package com.bms.eai.module.beans;

import org.springframework.http.HttpMethod;

/**
 * @author kul_sudhakar
 *
 */
public class RequestDetails /*extends FileUploadDetails*/ {

	private String url;
	private HttpMethod requestType;
	private String uriPathVariable;
	private FileUploadDetails fileUploadDetails;
	
	public RequestDetails(String url, HttpMethod requestType) {
		super();
		this.url = url;
		this.requestType = requestType;
	}
	
	public RequestDetails(String url, HttpMethod requestType,String uriPathVariable) {
		super();
		this.url = url;
		this.requestType = requestType;
		this.uriPathVariable = uriPathVariable;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public HttpMethod getRequestType() {
		return requestType;
	}

	public void setRequestType(HttpMethod requestType) {
		this.requestType = requestType;
	}

	public String getUriPathVariable() {
		return uriPathVariable;
	}

	public void setUriPathVariable(String uriPathVariable) {
		this.uriPathVariable = uriPathVariable;
	}

	public FileUploadDetails getFileUploadDetails() {
		return fileUploadDetails;
	}

	public void setFileUploadDetails(FileUploadDetails fileUploadDetails) {
		this.fileUploadDetails = fileUploadDetails;
	}

	@Override
	public String toString() {
		return "RequestDetails [url=" + url + ", requestType=" + requestType + ",uriPathVariable="+uriPathVariable+"]";
	}

}
