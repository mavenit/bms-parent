package com.bms.eai.module.prop.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("PropAssetsCategory")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropAssetsCategory  {

	@JsonProperty("pacType")
	private String pacType;
	
	public PropAssetsCategory() {
	}

	public String getPacType() {
		return this.pacType;
	}

	public void setPacType(String pacType) {
		this.pacType = pacType;
	}

}
