package com.bms.eai.module.prop.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author kul_sudhakar
 *
 */
@JsonRootName("state")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropState {

	@JsonProperty("id")
	private String id;
	
	@JsonProperty("country")
	private PropCountry country;
	
	@JsonProperty("name")
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PropCountry getCountry() {
		return country;
	}

	public void setCountry(PropCountry country) {
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
