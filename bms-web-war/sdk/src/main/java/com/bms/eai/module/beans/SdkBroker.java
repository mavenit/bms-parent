package com.bms.eai.module.beans;

import org.codehaus.jackson.JsonNode;
import org.springframework.http.ResponseEntity;

/**
 * @author kul_sudhakar
 *
 */
public class SdkBroker {

	private ResponseEntity<JsonNode> responseEntity;

	private JsonResponseBean jrbean;
	
	public SdkBroker () {}
	
	public SdkBroker (ResponseEntity<JsonNode> responseEntity) {this.responseEntity=responseEntity;}
	
	public SdkBroker (JsonResponseBean jrbean) {this.jrbean=jrbean;}
	
	public SdkBroker (ResponseEntity<JsonNode> responseEntity,JsonResponseBean jrbean) {
		this.responseEntity=responseEntity;
		this.jrbean=jrbean;
	}
	
	public ResponseEntity<JsonNode> getResponseEntity() {
		return responseEntity;
	}

	public void setResponseEntity(ResponseEntity<JsonNode> responseEntity) {
		this.responseEntity = responseEntity;
	}

	public JsonResponseBean getJrbean() {
		return jrbean;
	}

	public void setJrbean(JsonResponseBean jrbean) {
		this.jrbean = jrbean;
	}

}
