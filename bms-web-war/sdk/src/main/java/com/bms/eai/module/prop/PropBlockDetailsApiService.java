package com.bms.eai.module.prop;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.ResourceAccessException;

import com.bms.eai.module.beans.JsonResponseBean;
import com.bms.eai.module.beans.RequestDetails;
import com.bms.eai.module.core.SimpleDataAccess;
import com.bms.eai.module.prop.beans.PropBlockDetails;

/**
 * @author kul_sudhakar
 *
 */
@Service
public class PropBlockDetailsApiService extends AbstractPropDetailsRestApiClient<PropBlockDetails, JsonResponseBean> implements SimpleDataAccess<PropBlockDetails> {

	/*private SdkBroker preCheckData(String screenFlag) throws JsonParseException, JsonMappingException, IOException {

		final Optional<PbdOperations> operations = super.propertyClientApiAccess.getPropertyBlockDetailsOperations();

		if (!operations.isPresent()) {
			return new SdkBroker(this.generateJsonMsg(this.unknownSchemaConfig(ApiConstants.PROP_BLOCK_DETAILS)));
		}

		ResponseEntity<JsonNode> serviceStatus = null;

		if (StringUtils.hasText(screenFlag) && StringUtils.pathEquals(screenFlag, CREATE)) {
			serviceStatus = super.checkServiceEnable(operations.get().getCreate());
		} else if (StringUtils.hasText(screenFlag) && StringUtils.pathEquals(screenFlag, UPDATE)) {
			serviceStatus = super.checkServiceEnable(operations.get().getUpdate());
		} else if (StringUtils.hasText(screenFlag) && StringUtils.pathEquals(screenFlag, DELETE)) {
			serviceStatus = super.checkServiceEnable(operations.get().getDelete());
		} else if (StringUtils.hasText(screenFlag) && StringUtils.pathEquals(screenFlag, LOADALL)) {
			serviceStatus = super.checkServiceEnable(operations.get().getLoadall());
		} else if (StringUtils.hasText(screenFlag) && StringUtils.pathEquals(screenFlag, LOADBYID)) {
			serviceStatus = super.checkServiceEnable(operations.get().getLoadById());
		}

		if (serviceStatus == null || (serviceStatus != null && serviceStatus.getBody() == null)) {
			return new SdkBroker(serviceStatus);
		}

		Optional<JsonResponseBean> jrb = JsonApiUtil.transferToObject(serviceStatus.getBody(), JsonResponseBean.class);
		if (!jrb.isPresent()) {
			return new SdkBroker(this.generateJsonMsg(super.invalidResponseMessage()));
		}
		return new SdkBroker(jrb.get());
	}
*/
	private JsonResponseBean processOperations(String updatePropDetailsMasterId, String screenFlag, PropBlockDetails pdm)
			throws ResourceAccessException, Exception {

		String hostName = super.getPropDetailsServerDetails().getHostName();
		String portNo = super.getPropDetailsServerDetails().getPortNo();

		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(HTTP).append(hostName).append(HTTP_COLUMN).append(portNo).append(HTTP_SLASH)
				.append(HTTP_PROP_CONTEXT_PATH).append(HTTP_SLASH);
		urlBuilder.append(API_NAME).append(HTTP_SLASH).append(API_VERSION).append(HTTP_SLASH);
		urlBuilder.append(BLOCKDETAILS_RESOURCE_PATH).append(HTTP_SLASH);

		JsonResponseBean jrb = null;

		if (StringUtils.hasText(screenFlag) && StringUtils.pathEquals(screenFlag, CREATE)) {
			urlBuilder.append(CREATE);
			logger.info("[CREATE {}, RESOURCE URL : {}]", new Object[] {BLOCKDETAILS_RESOURCE_PATH, urlBuilder.toString() });
			jrb = super.execute(false,new RequestDetails(urlBuilder.toString(), HttpMethod.POST), pdm,
					super.generateResponseHandler(), JsonResponseBean.class);
		} else if (StringUtils.hasText(screenFlag) && StringUtils.pathEquals(screenFlag, UPDATE)) {
			urlBuilder.append(UPDATE);
			logger.info("[UPDATE {}, RESOURCE URL : {}]", new Object[] {BLOCKDETAILS_RESOURCE_PATH, urlBuilder.toString() });
			jrb = super.execute(false,new RequestDetails(urlBuilder.toString(), HttpMethod.PUT, updatePropDetailsMasterId),
					pdm, super.generateResponseHandler(), JsonResponseBean.class);
		} else if (StringUtils.hasText(screenFlag) && StringUtils.pathEquals(screenFlag, DELETE)) {
			urlBuilder.append(DELETE);
			logger.info("[DELETE {}, RESOURCE URL : {}]", new Object[] {BLOCKDETAILS_RESOURCE_PATH, urlBuilder.toString() });
			jrb = super.execute(false,new RequestDetails(urlBuilder.toString(), HttpMethod.DELETE, updatePropDetailsMasterId),
					pdm, super.generateResponseHandler(), JsonResponseBean.class);
		} else if (StringUtils.hasText(screenFlag) && StringUtils.pathEquals(screenFlag, LOADALL)) {
			urlBuilder.append(LOADALL);
			logger.info("[LOADALL {}, RESOURCE URL : {}]", new Object[] {BLOCKDETAILS_RESOURCE_PATH, urlBuilder.toString() });
			jrb = super.execute(false,new RequestDetails(urlBuilder.toString(), HttpMethod.GET), null,
					super.generateResponseHandler(), JsonResponseBean.class);
		} else if (StringUtils.hasText(screenFlag) && StringUtils.pathEquals(screenFlag, LOADBYID)) {
			urlBuilder.append(LOADBYID).append(HTTP_SLASH);
			logger.info("[LOADBYID {}, RESOURCE URL : {}]", new Object[] {BLOCKDETAILS_RESOURCE_PATH, urlBuilder.toString() });
			jrb = super.execute(false,new RequestDetails(urlBuilder.toString(), HttpMethod.GET, updatePropDetailsMasterId),
					null, super.generateResponseHandler(), JsonResponseBean.class);
		}
		logger.info("[{}, Result JsonResponseBean :{}]", new Object[] {BLOCKDETAILS_RESOURCE_PATH, jrb });
		return jrb;
	}
	
	public JsonResponseBean create(PropBlockDetails t) throws ResourceAccessException, Exception {
		logger.info("SDK CREATE ......");
		return this.processOperations(null,CREATE,t);
		//return null;
	}

	public JsonResponseBean update(String updateId, PropBlockDetails t) throws ResourceAccessException, Exception {
		return null;
	}

	public JsonResponseBean load(String id) throws ResourceAccessException, Exception {
		return null;
	}

	public JsonResponseBean delete(String id) throws ResourceAccessException, Exception {
		logger.info("SDK Delete Record ......");
		return null ;
	}
	
	@Override
	public JsonResponseBean getPage(Integer pageNumber, String fieldName) throws ResourceAccessException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
