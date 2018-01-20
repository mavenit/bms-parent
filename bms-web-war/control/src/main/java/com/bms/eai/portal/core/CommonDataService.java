package com.bms.eai.portal.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.ResourceAccessException;

import com.bms.eai.common.lib.ResourcePathConstants;
import com.bms.eai.common.lib.UserStatus;
import com.bms.eai.module.beans.JsonResponseBean;
import com.bms.eai.module.common.CommonCountryApiService;
import com.bms.eai.module.common.CommonPropTypeApiService;
import com.bms.eai.module.common.CommonStateApiService;
import com.bms.eai.module.common.beans.CommonBean;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author kul_sudhakar
 *
 */
@Service
public class CommonDataService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CommonCountryApiService countryApiService;
	
	@Autowired
	private CommonStateApiService  stateApiService;
	
	@Autowired
	private CommonPropTypeApiService propTypeApiService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public List<CommonBean> processStaticData(Map<String,Object> map) {
		
		JsonResponseBean responseEntity = null;
		
		try {
			String serviceName = String.valueOf(map.get(ResourcePathConstants.SERVICE_NAME));
			String operationName = String.valueOf(map.get(ResourcePathConstants.OPERATION_NAME));
			String id = String.valueOf(map.get(ResourcePathConstants.ID));
			String name = String.valueOf(map.get(ResourcePathConstants.NAME));
			CommonBean bean =  (CommonBean) map.get(ResourcePathConstants.BEAN);
			
			logger.info("[OPERATION NAME :{}]-[SERVICE NAME :{}]-[ID :{}]-[NAME :{}]-[BEAN :{}]",operationName,serviceName,id,name,bean);
			
			if(StringUtils.hasText(serviceName) && StringUtils.pathEquals(serviceName, ResourcePathConstants.COUNTRY_RESOURCE_PATH)) {
				
				if(StringUtils.hasText(operationName) && StringUtils.pathEquals(operationName, ResourcePathConstants.LOADALL)) {
					responseEntity = countryApiService.loadAll();
					
				}else if(StringUtils.hasText(operationName) && StringUtils.pathEquals(operationName, ResourcePathConstants.LOADBYID)) {
					responseEntity = countryApiService.load(id);
					
				}else if(StringUtils.hasText(operationName) && StringUtils.pathEquals(operationName, ResourcePathConstants.LOADBYNAME)) {
					responseEntity = countryApiService.loadByName(name);
					
				}else if(StringUtils.hasText(operationName) && StringUtils.pathEquals(operationName, ResourcePathConstants.CREATE)) {
					responseEntity = countryApiService.create(bean);
					
				}else if(StringUtils.hasText(operationName) && StringUtils.pathEquals(operationName, ResourcePathConstants.UPDATE)) {
					responseEntity = countryApiService.update(id,bean);
					
				}else if(StringUtils.hasText(operationName) && StringUtils.pathEquals(operationName, ResourcePathConstants.DELETE)) {
					responseEntity = countryApiService.delete(id);
				}
				
			} else if(StringUtils.hasText(serviceName) && StringUtils.pathEquals(serviceName, ResourcePathConstants.STATE_RESOURCE_PATH)) {
				
				if(StringUtils.hasText(operationName) && StringUtils.pathEquals(operationName, ResourcePathConstants.LOADALL)) {
					responseEntity = stateApiService.loadAll();
					
				}else if(StringUtils.hasText(operationName) && StringUtils.pathEquals(operationName, ResourcePathConstants.LOADBYID)) {
					responseEntity = stateApiService.load(id);
					
				}else if(StringUtils.hasText(operationName) && StringUtils.pathEquals(operationName, ResourcePathConstants.LOADBYNAME)) {
					responseEntity = stateApiService.loadByName(name);
					
				}else if(StringUtils.hasText(operationName) && StringUtils.pathEquals(operationName, ResourcePathConstants.CREATE)) {
					responseEntity = stateApiService.create(bean);
					
				}else if(StringUtils.hasText(operationName) && StringUtils.pathEquals(operationName, ResourcePathConstants.UPDATE)) {
					responseEntity = stateApiService.update(id,bean);
					
				}else if(StringUtils.hasText(operationName) && StringUtils.pathEquals(operationName, ResourcePathConstants.LOADBYCOUNTRY)) {
					responseEntity = stateApiService.loadByCountry(id);
					
				}else if(StringUtils.hasText(operationName) && StringUtils.pathEquals(operationName, ResourcePathConstants.DELETE)) {
					responseEntity = stateApiService.delete(id);
				}
				
			}else if(StringUtils.hasText(serviceName) && StringUtils.pathEquals(serviceName, ResourcePathConstants.PROPTYPE_RESOURCE_PATH)) {
				
				if(StringUtils.hasText(operationName) && StringUtils.pathEquals(operationName, ResourcePathConstants.LOADALL)) {
					responseEntity = propTypeApiService.loadAll();
					
				}else if(StringUtils.hasText(operationName) && StringUtils.pathEquals(operationName, ResourcePathConstants.LOADBYID)) {
					responseEntity = propTypeApiService.load(id);
					
				}else if(StringUtils.hasText(operationName) && StringUtils.pathEquals(operationName, ResourcePathConstants.LOADBYNAME)) {
					responseEntity = propTypeApiService.loadByName(name);
					
				}else if(StringUtils.hasText(operationName) && StringUtils.pathEquals(operationName, ResourcePathConstants.CREATE)) {
					responseEntity = propTypeApiService.create(bean);
					
				}else if(StringUtils.hasText(operationName) && StringUtils.pathEquals(operationName, ResourcePathConstants.UPDATE)) {
					responseEntity = propTypeApiService.update(id,bean);
					
				}else if(StringUtils.hasText(operationName) && StringUtils.pathEquals(operationName, ResourcePathConstants.DELETE)) {
					responseEntity = propTypeApiService.delete(id);
				}
			}
			
			logger.info("[RESPONSE :{}]-BY-[SERVICE NAME :{}]",responseEntity,serviceName);
			
			List<CommonBean> retList = new ArrayList<CommonBean>();
			
			if(responseEntity == null || (responseEntity!=null && 
										 StringUtils.hasText(responseEntity.getStatusCode()) && 
										 StringUtils.pathEquals(responseEntity.getStatusCode(), UserStatus.FAIL.statusCode()) ) ) {
				return retList;
			}
			
			String jsonResp = null;
			
			if(StringUtils.hasText(operationName) && StringUtils.pathEquals(operationName, ResourcePathConstants.LOADALL)) {
				jsonResp = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseEntity.getResponseBean());
				retList = objectMapper.readValue(jsonResp, new TypeReference<List<CommonBean>>(){});
				logger.info("[OPERATION NAME :{}]-BY-[RESPONSE ENTITY LIST Size :{}]",operationName,retList.size());
				return retList;
			}
			
			jsonResp = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseEntity.getResponseBean());
			CommonBean respBean = objectMapper.readValue(jsonResp, CommonBean.class);
			logger.info("[OPERATION NAME :{}]-BY-[RESPONSE ENTITY LIST Size :{}]",operationName,retList.size());
			retList.add(respBean);
			
			return retList;
			
		}catch (ResourceAccessException e) {
			logger.error(e.getMessage(),e);
			return Collections.emptyList();
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
			return Collections.emptyList();
		}
	}

}
