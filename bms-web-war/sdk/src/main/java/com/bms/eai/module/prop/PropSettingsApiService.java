package com.bms.eai.module.prop;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.ResourceAccessException;

import com.bms.eai.module.beans.JsonResponseBean;
import com.bms.eai.module.beans.RequestDetails;
import com.bms.eai.module.core.SimpleDataAccess;
import com.bms.eai.module.prop.beans.PropDetailsMaster;

/**
 * @author kul_sudhakar
 *
 */
@Component
public class PropSettingsApiService extends AbstractPropDetailsRestApiClient<PropDetailsMaster,JsonResponseBean> implements SimpleDataAccess<PropDetailsMaster>{

	private JsonResponseBean processOperations(String id,String screenFlag,Integer pageNumber,String fieldName,
											   PropDetailsMaster pdm) throws ResourceAccessException, Exception {
		
		String hostName = super.getPropDetailsServerDetails().getHostName();
		String portNo = super.getPropDetailsServerDetails().getPortNo();
		
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(HTTP).append(hostName).append(HTTP_COLUMN).append(portNo).append(HTTP_SLASH).append(HTTP_PROP_CONTEXT_PATH).append(HTTP_SLASH);
		urlBuilder.append(API_NAME).append(HTTP_SLASH).append(API_VERSION).append(HTTP_SLASH);
		urlBuilder.append(PROP_RESOURCE_PATH).append(HTTP_SLASH);
		JsonResponseBean jrb = null;
		
		if(StringUtils.hasText(screenFlag) && StringUtils.pathEquals(screenFlag, CREATE)){
			urlBuilder.append(CREATE);
			logger.info("[CREATE {}, RESOURCE URL : {}]",new Object[]{PROP_RESOURCE_PATH,urlBuilder.toString()});
			RequestDetails rd = new RequestDetails(urlBuilder.toString(), HttpMethod.POST);
			if(pdm.getFileUploadDetails()!=null) {rd.setFileUploadDetails(pdm.getFileUploadDetails());}
			jrb = super.execute(true,rd,pdm,super.generateResponseHandler(),JsonResponseBean.class);

		}else if(StringUtils.hasText(screenFlag) && StringUtils.pathEquals(screenFlag, UPDATE)){
			urlBuilder.append(UPDATE).append(HTTP_SLASH).append(id);
			logger.info("[UPDATE {}, RESOURCE URL : {}]",new Object[]{PROP_RESOURCE_PATH,urlBuilder.toString()});
			RequestDetails rd = new RequestDetails(urlBuilder.toString(), HttpMethod.PUT,id);
			jrb = super.execute(false,rd,pdm,super.generateResponseHandler(),JsonResponseBean.class);
			
		}else if(StringUtils.hasText(screenFlag) && StringUtils.pathEquals(screenFlag, DELETE)){
			urlBuilder.append(DELETE).append(HTTP_SLASH).append(id);
			logger.info("[DELETE {}, RESOURCE URL : {}]",new Object[]{PROP_RESOURCE_PATH,urlBuilder.toString()});
			jrb = super.execute(false,new RequestDetails(urlBuilder.toString(), HttpMethod.DELETE,id), 
		 					    pdm,super.generateResponseHandler(),JsonResponseBean.class);
			
		}else if(StringUtils.hasText(screenFlag) && StringUtils.pathEquals(screenFlag, LOADALL)){
			urlBuilder.append(LOADALL);
			logger.info("[LOADALL {}, RESOURCE URL : {}]",new Object[]{PROP_RESOURCE_PATH,urlBuilder.toString()});
			jrb = super.execute(false,new RequestDetails(urlBuilder.toString(), HttpMethod.GET), 
		 					    null,super.generateResponseHandler(),JsonResponseBean.class);
			
		}else if(StringUtils.hasText(screenFlag) && StringUtils.pathEquals(screenFlag, LOADBYID)){
			urlBuilder.append(LOADBYID).append(HTTP_SLASH).append(id);
			logger.info("[LOADBYID {}, RESOURCE URL : {}]",new Object[]{PROP_RESOURCE_PATH,urlBuilder.toString()});
			jrb = super.execute(false,new RequestDetails(urlBuilder.toString(), HttpMethod.GET,id), 
		 					    null,super.generateResponseHandler(),JsonResponseBean.class);
			
		}else if(StringUtils.hasText(screenFlag) && StringUtils.pathEquals(screenFlag, LOADBYPAGE)){
			// ?page=1&size=2&sort=id
			urlBuilder.append(LOADBYPAGE).append("?");
			urlBuilder.append(PAGE).append(EQUAL).append(pageNumber).append(AND);
			urlBuilder.append(SIZE).append(EQUAL).append(PAGE_SIZE);
			if(StringUtils.hasText(fieldName)) {
				urlBuilder.append(AND).append(SORT).append(EQUAL).append(fieldName);
			}
			logger.info("[LOADBYPAGE {}, RESOURCE URL : {}]",new Object[]{PROP_RESOURCE_PATH,urlBuilder.toString()});
			jrb = super.execute(false,new RequestDetails(urlBuilder.toString(), HttpMethod.GET,id), 
		 					    null,super.generateResponseHandler(),JsonResponseBean.class);
		}
		
		logger.info("[{}, Result JsonResponseBean :{}]",new Object[]{PROP_RESOURCE_PATH,jrb});
		return jrb;
	}

	public JsonResponseBean create(PropDetailsMaster propDetailsMaster) throws ResourceAccessException, Exception {
		logger.info("SDK Create Record ......");
		return this.processOperations(null,CREATE,null,null,propDetailsMaster);
	}

	public JsonResponseBean update(String updateId, PropDetailsMaster propDetailsMaster) throws ResourceAccessException, Exception {
		logger.info("SDK Update Record ......");
		return this.processOperations(updateId,UPDATE,null,null,propDetailsMaster);
	}

	public JsonResponseBean load(String id) throws ResourceAccessException, Exception {
		logger.info("SDK Load Record ......");
		return this.processOperations(id,LOADBYID,null,null,null);
	}
	
	public JsonResponseBean delete(String id) throws ResourceAccessException, Exception {
		logger.info("SDK Delete Record ......");
		return this.processOperations(id,DELETE,null,null,null);
	}
	
	public JsonResponseBean getPage(Integer pageNumber,String fieldName) throws ResourceAccessException, Exception {
		logger.info("SDK getPage Record ......");
		return this.processOperations(null,LOADBYPAGE,pageNumber,fieldName,null);
	}
	
}
