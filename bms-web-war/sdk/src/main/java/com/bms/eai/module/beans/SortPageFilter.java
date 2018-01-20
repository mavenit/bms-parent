package com.bms.eai.module.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author kul_sudhakar
 *
 */
@JsonRootName("sort")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SortPageFilter /*extends Sort*/ {

	/*
	 * "direction": "ASC", "property": "id", "ignoreCase": false,
	 * "nullHandling": "NATIVE", "descending": false, "ascending": true
	 */

	@JsonProperty("direction")
	private String direction;

	@JsonProperty("property")
	private String property;

	@JsonProperty("ignoreCase")
	private Boolean ignoreCase;

	@JsonProperty("nullHandling")
	private String nullHandling;

	@JsonProperty("descending")
	private Boolean descending;

	@JsonProperty("ascending")
	private Boolean ascending;

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Boolean getIgnoreCase() {
		return ignoreCase;
	}

	public void setIgnoreCase(Boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}

	public String getNullHandling() {
		return nullHandling;
	}

	public void setNullHandling(String nullHandling) {
		this.nullHandling = nullHandling;
	}

	public Boolean getDescending() {
		return descending;
	}

	public void setDescending(Boolean descending) {
		this.descending = descending;
	}

	public Boolean getAscending() {
		return ascending;
	}

	public void setAscending(Boolean ascending) {
		this.ascending = ascending;
	}

}
