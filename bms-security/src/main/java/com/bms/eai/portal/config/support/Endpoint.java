package com.bms.eai.portal.config.support;

import org.springframework.http.HttpMethod;

/**
 * @author kul_sudhakar
 *
 */
public class Endpoint {

	private String resourcePath;
	private HttpMethod httpMethod;

	public Endpoint(String resourcePath, HttpMethod httpMethod) {
		this.resourcePath = resourcePath;
		this.httpMethod = httpMethod;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}

}
