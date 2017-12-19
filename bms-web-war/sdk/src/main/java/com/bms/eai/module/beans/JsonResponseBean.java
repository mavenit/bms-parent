package com.bms.eai.module.beans;

/**
 * @author kul_sudhakar
 *
 */
public class JsonResponseBean {

	private String statusCode;
	private String statusMessage;
	private Object responseBean;

	public JsonResponseBean() {}
	
	public JsonResponseBean(String statusCode) {this.statusCode=statusCode;}
	
	public JsonResponseBean(String statusCode,String statusMessage) {
		this.statusCode=statusCode;
		this.statusMessage=statusMessage;
	}
	
	public JsonResponseBean(String statusCode,String statusMessage,Object responseBean) {
		this.statusCode=statusCode;
		this.statusMessage=statusMessage;
		this.responseBean=responseBean;
	}

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

	public Object getResponseBean() {
		return responseBean;
	}

	public void setResponseBean(Object responseBean) {
		this.responseBean = responseBean;
	}

}
