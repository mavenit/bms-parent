package com.bms.eai.module.common;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.ResourceAccessException;

import com.bms.eai.module.beans.JsonResponseBean;
import com.bms.eai.module.beans.RequestDetails;
import com.bms.eai.module.common.beans.CommonBean;
import com.bms.eai.module.core.CommonDataAccess;

/**
 * @author kul_sudhakar
 *
 */
@Service
public class CommonPropTypeApiService extends AbstractCommonRestApiClient<CommonBean,JsonResponseBean> implements CommonDataAccess<CommonBean>{
 
	private JsonResponseBean processOperations(String id, String screenFlag,CommonBean reqJsonBean) throws ResourceAccessException, Exception {

		String hostName = super.getCommonModuleServerDetails().getHostName();
		String portNo = super.getCommonModuleServerDetails().getPortNo();

		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(HTTP).append(hostName).append(HTTP_COLUMN).append(portNo).append(HTTP_SLASH)
				  .append(HTTP_STATIC_CONTEXT_PATH).append(HTTP_SLASH);
		urlBuilder.append(API_NAME).append(HTTP_SLASH).append(API_VERSION).append(HTTP_SLASH);
		urlBuilder.append(PROPTYPE_RESOURCE_PATH).append(HTTP_SLASH);

		JsonResponseBean jrb = null;

		if (StringUtils.hasText(screenFlag) && StringUtils.pathEquals(screenFlag, CREATE)) {
				urlBuilder.append(CREATE);
				logger.info("[CREATE {}, RESOURCE URL : {}]",new Object[] { PROPTYPE_RESOURCE_PATH, urlBuilder.toString() });
				jrb = super.execute(false,new RequestDetails(urlBuilder.toString(), HttpMethod.POST), reqJsonBean,
						super.generateResponseHandler(), JsonResponseBean.class);
				
		} else if (StringUtils.hasText(screenFlag) && StringUtils.pathEquals(screenFlag, UPDATE)) {
				urlBuilder.append(UPDATE).append(HTTP_SLASH).append(id);
				logger.info("[UPDATE {}, RESOURCE URL : {}]",new Object[] { PROPTYPE_RESOURCE_PATH, urlBuilder.toString() });
				jrb = super.execute(false,new RequestDetails(urlBuilder.toString(), HttpMethod.PUT, id),
						reqJsonBean, super.generateResponseHandler(), JsonResponseBean.class);
				
		} else if (StringUtils.hasText(screenFlag) && StringUtils.pathEquals(screenFlag, DELETE)) {
				urlBuilder.append(DELETE).append(HTTP_SLASH).append(id);
				logger.info("[DELETE {}, RESOURCE URL : {}]",new Object[] { PROPTYPE_RESOURCE_PATH, urlBuilder.toString() });
				jrb = super.execute(false,new RequestDetails(urlBuilder.toString(), HttpMethod.DELETE, id),
						reqJsonBean, super.generateResponseHandler(), JsonResponseBean.class);
				
		} else if (StringUtils.hasText(screenFlag) && StringUtils.pathEquals(screenFlag, LOADALL)) {
				urlBuilder.append(LOADALL);
				logger.info("[LOADALL {}, RESOURCE URL : {}]",new Object[] { PROPTYPE_RESOURCE_PATH, urlBuilder.toString() });
				jrb = super.execute(false,new RequestDetails(urlBuilder.toString(), HttpMethod.GET), null,
						super.generateResponseHandler(), JsonResponseBean.class);
				
		} else if (StringUtils.hasText(screenFlag) && StringUtils.pathEquals(screenFlag, LOADBYID)) {
				urlBuilder.append(LOADBYID).append(HTTP_SLASH).append(id);
				logger.info("[LOADBYID {}, RESOURCE URL : {}]",	new Object[] { PROPTYPE_RESOURCE_PATH, urlBuilder.toString() });
				jrb = super.execute(false,new RequestDetails(urlBuilder.toString(), HttpMethod.GET, id),
						null, super.generateResponseHandler(), JsonResponseBean.class);
				
		}else if (StringUtils.hasText(screenFlag) && StringUtils.pathEquals(screenFlag, LOADBYNAME)) {
			urlBuilder.append(LOADBYNAME).append(HTTP_SLASH).append(id);
			logger.info("[LOADBYNAME {}, RESOURCE URL : {}]", new Object[] {COUNTRY_RESOURCE_PATH,urlBuilder.toString() });
			jrb = super.execute(false,new RequestDetails(urlBuilder.toString(), HttpMethod.GET, id),
					null, super.generateResponseHandler(), JsonResponseBean.class);
		}
		logger.info("[{}, Result JsonResponseBean :{}]", new Object[] { PROPTYPE_RESOURCE_PATH, jrb });
		return jrb;
	}
	
	public JsonResponseBean create(CommonBean t) throws ResourceAccessException, Exception {
		logger.info("SDK CREATE ......");
		return this.processOperations(null,CREATE,t);
	}

	public JsonResponseBean update(String updateId, CommonBean t) throws ResourceAccessException, Exception {
		logger.info("SDK Update Record ......");
		return this.processOperations(updateId,UPDATE,t);
	}

	public JsonResponseBean loadAll() throws ResourceAccessException, Exception {
		logger.info("SDK Load All ......");
		return this.processOperations(null,LOADALL,null);
	}
	
	public JsonResponseBean delete(String id) throws ResourceAccessException, Exception {
		logger.info("SDK DELETE Record ......");
		return this.processOperations(id,DELETE,null);
	} 
	
	public JsonResponseBean load(String id) throws ResourceAccessException, Exception {
		logger.info("SDK Load Record ......");
		return this.processOperations(id,LOADBYID,null);
	} 
	
	public JsonResponseBean loadByName(String name) throws ResourceAccessException, Exception {
		logger.info("SDK Load Record ......");
		return this.processOperations(name,LOADBYNAME,null);
	}
	 

	@Override
	public JsonResponseBean getPage(Integer pageNumber, String fieldName) throws ResourceAccessException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
