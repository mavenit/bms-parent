package com.bms.eai.portal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author kul_sudhakar
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonResponseBean {

	private String statusCode;
	private String statusMessage;
	private Object responseObj;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public Object getResponseObj() {
		return responseObj;
	}

	public void setResponseObj(Object responseObj) {
		this.responseObj = responseObj;
	}

}
