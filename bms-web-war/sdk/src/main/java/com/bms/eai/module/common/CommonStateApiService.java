package com.bms.eai.module.common;

import java.io.IOException;
import java.util.Optional;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.ResourceAccessException;

import com.bms.eai.common.lib.ApiConstants;
import com.bms.eai.common.lib.JsonApiUtil;
import com.bms.eai.module.api.config.xml.StateOperations;
import com.bms.eai.module.beans.JsonResponseBean;
import com.bms.eai.module.beans.RequestDetails;
import com.bms.eai.module.beans.SdkBroker;
import com.bms.eai.module.common.beans.CommonBean;

/**
 * @author kul_sudhakar
 *
 */
@Service
public class CommonStateApiService extends AbstractCommonRestApiClient<CommonBean,JsonResponseBean> {

	
	private SdkBroker preCheckData(String screenFlag) throws JsonParseException, JsonMappingException, IOException {
		
		final Optional<StateOperations> stateOperations = super.clientOperationsApiAccess.getStateOperations();
		
		if(!stateOperations.isPresent()) {
			return new SdkBroker(this.generateJsonMsg(this.unknownSchemaConfig(ApiConstants.STATE)));
		}
		
		ResponseEntity<JsonNode> serviceStatus = null; 
		
		if(StringUtils.hasText(screenFlag) && StringUtils.pathEquals(screenFlag, CREATE)){
			serviceStatus = super.checkServiceEnable(stateOperations.get().getCreate());
		}else if(StringUtils.hasText(screenFlag) && StringUtils.pathEquals(screenFlag, UPDATE)){
			serviceStatus = super.checkServiceEnable(stateOperations.get().getUpdate());
		}else if(StringUtils.hasText(screenFlag) && StringUtils.pathEquals(screenFlag, DELETE)){
			serviceStatus = super.checkServiceEnable(stateOperations.get().getDelete());
		}else if(StringUtils.hasText(screenFlag) && StringUtils.pathEquals(screenFlag, LOADALL)){
			serviceStatus = super.checkServiceEnable(stateOperations.get().getLoadall());
		}else if(StringUtils.hasText(screenFlag) && StringUtils.pathEquals(screenFlag, LOADBYID)){
			serviceStatus = super.checkServiceEnable(stateOperations.get().getLoadById());
		}
		
		if(serviceStatus == null || (serviceStatus!=null && serviceStatus.getBody() == null) ) {
			return new SdkBroker(serviceStatus);
		}
		
		Optional<JsonResponseBean> jrb = JsonApiUtil.transferToObject(serviceStatus.getBody(),JsonResponseBean.class);
		if(!jrb.isPresent()) {
			return new SdkBroker(this.generateJsonMsg(super.invalidResponseMessage())); 
		}
		return new SdkBroker(jrb.get());
	}
	
	private JsonResponseBean stateOperations(String updatePropDetailsMasterId, String screenFlag,CommonBean reqJsonBean) throws ResourceAccessException, Exception {

		String hostName = super.getCommonModuleServerDetails().getHostName();
		String portNo = super.getCommonModuleServerDetails().getPortNo();

		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(HTTP).append(hostName).append(HTTP_COLUMN).append(portNo).append(HTTP_SLASH)
				.append(HTTP_PROP_CONTEXT_PATH).append(HTTP_SLASH);
		urlBuilder.append(API_NAME).append(HTTP_SLASH).append(API_VERSION).append(HTTP_SLASH);
		urlBuilder.append(STATE_RESOURCE_PATH).append(HTTP_SLASH);

		JsonResponseBean jrb = null;

		if (StringUtils.hasText(screenFlag) && StringUtils.pathEquals(screenFlag, CREATE)) {
				urlBuilder.append(CREATE);
				logger.info("[CREATE {}, RESOURCE URL : {}]", new Object[] {STATE_RESOURCE_PATH, urlBuilder.toString() });
				jrb = super.execute(new RequestDetails(urlBuilder.toString(), HttpMethod.POST), reqJsonBean,
						super.generateResponseHandler(), JsonResponseBean.class);
		} else if (StringUtils.hasText(screenFlag) && StringUtils.pathEquals(screenFlag, UPDATE)) {
				urlBuilder.append(UPDATE);
				logger.info("[UPDATE {}, RESOURCE URL : {}]", new Object[] {STATE_RESOURCE_PATH,urlBuilder.toString() });
				jrb = super.execute(new RequestDetails(urlBuilder.toString(), HttpMethod.PUT, updatePropDetailsMasterId),
						reqJsonBean, super.generateResponseHandler(), JsonResponseBean.class);
		} else if (StringUtils.hasText(screenFlag) && StringUtils.pathEquals(screenFlag, DELETE)) {
				urlBuilder.append(DELETE);
				logger.info("[DELETE {}, RESOURCE URL : {}]", new Object[] {STATE_RESOURCE_PATH,urlBuilder.toString() });
				jrb = super.execute(new RequestDetails(urlBuilder.toString(), HttpMethod.DELETE, updatePropDetailsMasterId),
						reqJsonBean, super.generateResponseHandler(), JsonResponseBean.class);
		} else if (StringUtils.hasText(screenFlag) && StringUtils.pathEquals(screenFlag, LOADALL)) {
				urlBuilder.append(LOADALL);
				logger.info("[LOADALL {}, RESOURCE URL : {}]", new Object[] {STATE_RESOURCE_PATH,urlBuilder.toString() });
				jrb = super.execute(new RequestDetails(urlBuilder.toString(), HttpMethod.GET), null,
						super.generateResponseHandler(), JsonResponseBean.class);
		} else if (StringUtils.hasText(screenFlag) && StringUtils.pathEquals(screenFlag, LOADBYID)) {
				urlBuilder.append(LOADBYID).append(HTTP_SLASH);
				logger.info("[LOADBYID {}, RESOURCE URL : {}]", new Object[] {STATE_RESOURCE_PATH,urlBuilder.toString() });
				jrb = super.execute(new RequestDetails(urlBuilder.toString(), HttpMethod.GET, updatePropDetailsMasterId),
						null, super.generateResponseHandler(), JsonResponseBean.class);
		}
		logger.info("[{}, Result JsonResponseBean :{}]", new Object[] {STATE_RESOURCE_PATH,jrb });
		return jrb;
	}
	
	
	public ResponseEntity<JsonNode> processStateOperations(final CommonBean reqJsonBean,
														   final String updatePropDetailsMasterId, 
														   final String screenFlag) throws ResourceAccessException, Exception {

		SdkBroker sb = this.preCheckData(screenFlag);

		ResponseEntity<JsonNode> serviceStatus = sb.getResponseEntity();

		if (serviceStatus != null) {
			return serviceStatus;
		}

		JsonResponseBean jrbean = sb.getJrbean();

		if (StringUtils.hasText(jrbean.getStatusCode())	&& StringUtils.pathEquals(jrbean.getStatusCode(), ERROR_STATUS_CODE)) {
			return this.generateJsonMsg(super.invalidResponseMessage());
		} else if (StringUtils.hasText(jrbean.getStatusCode()) && !StringUtils.pathEquals(jrbean.getStatusCode(), SUCCESS_STATUS_CODE)) {
			return this.generateJsonMsg(super.invalidParseResponseMessage());
		} else {
			jrbean = null;
			jrbean = this.stateOperations(updatePropDetailsMasterId, screenFlag, reqJsonBean);
			return this.generateJsonMsg(jrbean);
		}
	}
	
}
