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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.ResponseErrorHandler;

import com.bms.eai.common.lib.ApiConstants;
import com.bms.eai.common.lib.JsonApiUtil;
import com.bms.eai.common.lib.ResourcePathConstants;
import com.bms.eai.module.beans.FileMessageResource;
import com.bms.eai.module.beans.JsonResponseBean;
import com.bms.eai.module.beans.RequestDetails;

/**
 * @author kul_sudhakar
 *
 */
public abstract class AbstractModuleConfig<T extends AbstractSdkEntity,V extends AbstractSdkEntity> extends AbstractRestTemplate implements ApiConstants,ResourcePathConstants {

	protected Class<T> requestDtoClass;
	
	protected Class<V> responseDtoClass;
	
	@SuppressWarnings("unchecked")
	public AbstractModuleConfig() {
		super();
		this.requestDtoClass =(Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.responseDtoClass =(Class<V>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	protected V execute(boolean fileUpload,RequestDetails requestDetails, T data, ResponseErrorHandler errorHandler,
						Class<V> genericClass) throws ResourceAccessException, Exception {
		
		logger.info("[COMES TO EXCUTE T-data :"+data+"]");
		ResponseEntity<V> response = null;
		HttpHeaders requestHeaders = new HttpHeaders();
		super.setErrorHandler(errorHandler);
		
		if(fileUpload && requestDetails.getFileUploadDetails()!=null) {
			
			 requestHeaders.setAccept(Collections.singletonList(new MediaType("application","json")));
			 requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
			 requestHeaders.setContentDispositionFormData("message", null);
			 
			 MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
			 //bodyMap.add("file", new FileMessageResource(FileUtils.readFileToByteArray(new File("d:/DellLaptop.pdf")), "DellLaptop.pdf"));
			 bodyMap.add("file", new FileMessageResource(requestDetails.getFileUploadDetails().getBytes(),requestDetails.getFileUploadDetails().getOriginalFilename()));
			 bodyMap.add("reqJson", data);
			 HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(bodyMap, requestHeaders);
		     response = super.exchange(requestDetails.getUrl(), 
									   requestDetails.getRequestType(),
									   entity, 
									   responseDtoClass);
		} else {
			requestHeaders.setAccept(Collections.singletonList(new MediaType("application","json")));
			requestHeaders.setContentType(new MediaType("application","json"));
			
			HttpEntity<T> entity = new HttpEntity<T>(data, requestHeaders);
			if(requestDetails!=null && StringUtils.hasText(requestDetails.getUriPathVariable())) {
				logger.info("[111 requestDetails.getUriPathVariable() :"+requestDetails.getUriPathVariable()+"]");
				response = super.exchange(requestDetails.getUrl(), 
										  requestDetails.getRequestType(),
			 							  entity, 
			 							  responseDtoClass,
			 							  requestDetails.getUriPathVariable());
			}else if(requestDetails!=null && !StringUtils.hasText(requestDetails.getUriPathVariable())) {
				logger.info("[222 requestDetails.getUriPathVariable() :"+requestDetails.getUriPathVariable()+"]");
				response = super.exchange(requestDetails.getUrl(), 
										  requestDetails.getRequestType(),
			 							  entity, 
			 							  responseDtoClass);
			}
			
		}
		
		return (response!=null&&response.getBody()!=null)?response.getBody():null;
	}
	
	protected ResponseEntity<JsonNode> generateJsonMsg(final JsonResponseBean message) {
		Optional<JsonNode> respNode = JsonApiUtil.toJsonNode(message);
		if(!respNode.isPresent()) {
			return new ResponseEntity<JsonNode>(JsonNodeFactory.instance.nullNode(), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<JsonNode>(respNode.get(), HttpStatus.OK);
	}
	
	protected JsonResponseBean enableService() {
		return new JsonResponseBean(SUCCESS_STATUS_CODE,new StringBuilder().append(ApiConstants.ENABLE).toString());
	}
	
	protected JsonResponseBean unknownSchemaConfig(final String propSubModuleName) {
		return new JsonResponseBean(ERROR_STATUS_CODE,new StringBuilder().append(ApiConstants.INVALID).append(ApiConstants.SINGLE_SPACE).append(propSubModuleName)
	  			  .append(ApiConstants.SINGLE_SPACE).append(ApiConstants.CONFIG).append(ApiConstants.SINGLE_SPACE)
	  			  .append(ApiConstants.CONTACT_ADMIN).toString());
	}
	
	protected JsonResponseBean unknownError() {
		return new JsonResponseBean(ERROR_STATUS_CODE,new StringBuilder().append(ApiConstants.UNKNOWN).append(ApiConstants.SINGLE_SPACE).append(ApiConstants.ERROR)
		  		  .append(ApiConstants.SINGLE_SPACE).append(ApiConstants.CONTACT_ADMIN).toString());
	}
	
	protected JsonResponseBean invalidStatus() {
		return new JsonResponseBean(ERROR_STATUS_CODE,new StringBuilder().append(ApiConstants.INVALID).append(ApiConstants.SINGLE_SPACE).append(ApiConstants.STATUS)
				  .append(ApiConstants.SINGLE_SPACE).append(ApiConstants.CONTACT_ADMIN).toString());
	}
	
	protected JsonResponseBean disableService() {
		return new JsonResponseBean(ERROR_STATUS_CODE,new StringBuilder().append(ApiConstants.SERVICE_DISABLE).append(ApiConstants.SINGLE_SPACE).append(ApiConstants.CONTACT_ADMIN).toString());
	}
	
	protected JsonResponseBean invalidParseResponseMessage() {
		return new JsonResponseBean(ERROR_STATUS_CODE,new StringBuilder().append(ApiConstants.ERROR_PARSE_RESP_MSG).append(ApiConstants.SINGLE_SPACE).append(ApiConstants.CONTACT_ADMIN).toString());
	}
	
	protected JsonResponseBean invalidResponseMessage() {
		return new JsonResponseBean(ERROR_STATUS_CODE,new StringBuilder().append(ApiConstants.ERROR_RESP_MSG).append(ApiConstants.SINGLE_SPACE).append(ApiConstants.CONTACT_ADMIN).toString());
	}
	
	protected JsonResponseBean emptyStatus() {
		return new JsonResponseBean(ERROR_STATUS_CODE,new StringBuilder().append(ApiConstants.EMPTY).append(ApiConstants.SINGLE_SPACE).append(ApiConstants.STATUS)
					.append(ApiConstants.SINGLE_SPACE).append(ApiConstants.CONTACT_ADMIN).toString());
	}
}
