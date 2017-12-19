package com.bms.eai.portal.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.bms.eai.portal.config.HeaderRequestInterceptor;
import com.bms.eai.portal.constants.ServiceUrlConstants;
import com.bms.eai.portal.lib.CmnUtil;
import com.bms.eai.portal.lib.JsonUtil;
import com.bms.eai.portal.model.JsonResponseBean;

/**
 * @author kul_sudhakar
 *
 */
public abstract class AbstractRestTemplate implements InitializingBean {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected static final String SESSION_USERNAME = "USER_NAME";
	protected static final String SESSION_USER_FULL_NAME = "USER_FULL_NAME";
	
	//protected  RestTemplate restTemplate;
	protected  RestTemplate restTemplate;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		restTemplate = new RestTemplate();
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add(new HeaderRequestInterceptor("Accept", MediaType.APPLICATION_JSON_VALUE));

		/*HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);*/
		
		restTemplate.setInterceptors(interceptors);
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	}
	
	protected HttpEntity<?> requestEntity(){ 
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Collections.singletonList(new MediaType("application","json")));
		requestHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
		return requestEntity;
	}
	
	protected HttpEntity<?> requestEntityPymt(){ 
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Collections.singletonList(new MediaType("application","text/html")));
		requestHeaders.add("Content-Type", MediaType.TEXT_HTML_VALUE);
		HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
		return requestEntity;
	}
	
	protected JsonResponseBean getServiceResponse(String url, Object obj){
		try {
			logger.info("JsonRespons");
			JsonResponseBean jrb = restTemplate.postForObject(ServiceUrlConstants.SIGNUP_URL, obj, JsonResponseBean.class);
			if(!CmnUtil.isObjNull(jrb)) {
				logger.info("[Status message :"+jrb.getStatusMessage()+"]-[Status Code :"+jrb.getStatusCode()+"]");
				JsonNode jn = JsonUtil.toJsonNode(jrb.getResponseObj());
				return jrb;
			}
			return null;
			 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
