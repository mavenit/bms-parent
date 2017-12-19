package com.bms.eai.module.prop.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("PropDuration")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropDuration  {

	@JsonProperty("pdType")
	private String pdType;
	
	@JsonProperty("propServiceProviders")
	private List<PropServiceProvider> propServiceProviders ;

	public PropDuration() {
	}

	public String getPdType() {
		return this.pdType;
	}

	public void setPdType(String pdType) {
		this.pdType = pdType;
	}

	public List<PropServiceProvider> getPropServiceProviders() {
		return this.propServiceProviders;
	}

	public void setPropServiceProviders(List<PropServiceProvider> propServiceProviders) {
		this.propServiceProviders = propServiceProviders;
	}


}
