package com.bms.eai.module.prop.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("PropFacilities")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropFacilities   {

	@JsonProperty("propDetailsMaster")
	private PropDetailsMaster propDetailsMaster;
	
	@JsonProperty("pfType")
	private String pfType;
	
	public PropFacilities() {
	}

	public PropDetailsMaster getPropDetailsMaster() {
		return this.propDetailsMaster;
	}

	public void setPropDetailsMaster(PropDetailsMaster propDetailsMaster) {
		this.propDetailsMaster = propDetailsMaster;
	}

	public String getPfType() {
		return this.pfType;
	}

	public void setPfType(String pfType) {
		this.pfType = pfType;
	}

}
