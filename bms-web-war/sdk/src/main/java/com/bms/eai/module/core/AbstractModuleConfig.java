package com.bms.eai.module.core;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.Optional;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.ResponseErrorHandler;

import com.bms.eai.common.lib.ApiConstants;
import com.bms.eai.common.lib.JsonApiUtil;
import com.bms.eai.common.lib.ResourcePathConstants;
import com.bms.eai.module.beans.JsonResponseBean;
import com.bms.eai.module.beans.RequestDetails;

/**
 * @author kul_sudhakar
 *
 */
public abstract class AbstractModuleConfig<T,V> extends AbstractRestTemplate implements ApiConstants,ResourcePathConstants {

	protected Class<T> requestDtoClass;
	
	protected Class<V> responseDtoClass;
	
	@SuppressWarnings("unchecked")
	public AbstractModuleConfig() {
		super();
		this.requestDtoClass =(Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.responseDtoClass =(Class<V>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	protected V execute(RequestDetails requestDetails, T data, ResponseErrorHandler errorHandler,
						Class<V> genericClass) throws ResourceAccessException, Exception {
		
		super.setErrorHandler(errorHandler);
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Collections.singletonList(new MediaType("application","json")));
		requestHeaders.setContentType(new MediaType("application","json"));
		HttpEntity<T> entity = new HttpEntity<T>(data, requestHeaders);
		ResponseEntity<V> response = null;
		if(requestDetails!=null && StringUtils.hasText(requestDetails.getUriPathVariable())) {
			response = super.exchange(requestDetails.getUrl(), 
									  requestDetails.getRequestType(),
		 							  entity, 
		 							  responseDtoClass,
		 							  requestDetails.getUriPathVariable());
		}
		
		if(requestDetails!=null && !StringUtils.hasText(requestDetails.getUriPathVariable())) {
			response = super.exchange(requestDetails.getUrl(), 
									  requestDetails.getRequestType(),
		 							  entity, 
		 							  responseDtoClass);
		}
		return (response!=null&&response.getBody()!=null)?response.getBody():null;
	}
	
	protected ResponseEntity<JsonNode> checkServiceEnable(String status) {
		
		if(!StringUtils.hasText(status)) {
				return this.generateJsonMsg(this.emptyStatus());
		}else if(StringUtils.hasText(status) && StringUtils.pathEquals(status, ApiConstants.DISABLE)) {
				return this.generateJsonMsg(this.disableService());
		}else if(StringUtils.hasText(status) && !StringUtils.pathEquals(status, ApiConstants.ENABLE)) {
				return this.generateJsonMsg(this.invalidStatus());
		}else if(StringUtils.hasText(status) && StringUtils.pathEquals(status, ApiConstants.ENABLE)) {
				return this.generateJsonMsg(this.enableService());
		}else {
				return this.generateJsonMsg(this.unknownError());
		}
	}
	
	protected ResponseEntity<JsonNode> generateJsonMsg(final JsonResponseBean message) {
		Optional<JsonNode> respNode = JsonApiUtil.toJsonNode(message);
		if(!respNode.isPresent()) {
			return new ResponseEntity<JsonNode>(JsonNodeFactory.instance.nullNode(), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<JsonNode>(respNode.get(), HttpStatus.OK);
	}
	
	private JsonResponseBean enableService() {
		return new JsonResponseBean(SUCCESS_STATUS_CODE,new StringBuilder().append(ApiConstants.ENABLE).toString());
	}
	
	protected JsonResponseBean unknownSchemaConfig(final String propSubModuleName) {
		return new JsonResponseBean(ERROR_STATUS_CODE,new StringBuilder().append(ApiConstants.INVALID).append(ApiConstants.SINGLE_SPACE).append(propSubModuleName)
	  			  .append(ApiConstants.SINGLE_SPACE).append(ApiConstants.CONFIG).append(ApiConstants.SINGLE_SPACE)
	  			  .append(ApiConstants.CONTACT_ADMIN).toString());
	}
	
	private JsonResponseBean unknownError() {
		return new JsonResponseBean(ERROR_STATUS_CODE,new StringBuilder().append(ApiConstants.UNKNOWN).append(ApiConstants.SINGLE_SPACE).append(ApiConstants.ERROR)
		  		  .append(ApiConstants.SINGLE_SPACE).append(ApiConstants.CONTACT_ADMIN).toString());
	}
	
	private JsonResponseBean invalidStatus() {
		return new JsonResponseBean(ERROR_STATUS_CODE,new StringBuilder().append(ApiConstants.INVALID).append(ApiConstants.SINGLE_SPACE).append(ApiConstants.STATUS)
				  .append(ApiConstants.SINGLE_SPACE).append(ApiConstants.CONTACT_ADMIN).toString());
	}
	
	private JsonResponseBean disableService() {
		return new JsonResponseBean(ERROR_STATUS_CODE,new StringBuilder().append(ApiConstants.SERVICE_DISABLE).append(ApiConstants.SINGLE_SPACE).append(ApiConstants.CONTACT_ADMIN).toString());
	}
	
	protected JsonResponseBean invalidParseResponseMessage() {
		return new JsonResponseBean(ERROR_STATUS_CODE,new StringBuilder().append(ApiConstants.ERROR_PARSE_RESP_MSG).append(ApiConstants.SINGLE_SPACE).append(ApiConstants.CONTACT_ADMIN).toString());
	}
	
	protected JsonResponseBean invalidResponseMessage() {
		return new JsonResponseBean(ERROR_STATUS_CODE,new StringBuilder().append(ApiConstants.ERROR_RESP_MSG).append(ApiConstants.SINGLE_SPACE).append(ApiConstants.CONTACT_ADMIN).toString());
	}
	
	private JsonResponseBean emptyStatus() {
		return new JsonResponseBean(ERROR_STATUS_CODE,new StringBuilder().append(ApiConstants.EMPTY).append(ApiConstants.SINGLE_SPACE).append(ApiConstants.STATUS)
					.append(ApiConstants.SINGLE_SPACE).append(ApiConstants.CONTACT_ADMIN).toString());
	}
}
