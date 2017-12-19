package com.bms.eai.module.prop.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("PropServiceProviderCategory")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true) 
public class PropServiceProviderCategory {

	@JsonProperty("pspcName")
	private String pspcName;
	
	@JsonProperty("pspcDesc")
	private String pspcDesc;
	
	@JsonProperty("propAssetses")
	private List<PropAssets> propAssetses;
	
	@JsonProperty("propServiceProviders")
	private List<PropServiceProvider> propServiceProviders ;

	public PropServiceProviderCategory() {
	}

	public String getPspcName() {
		return this.pspcName;
	}

	public void setPspcName(String pspcName) {
		this.pspcName = pspcName;
	}

	public String getPspcDesc() {
		return this.pspcDesc;
	}

	public void setPspcDesc(String pspcDesc) {
		this.pspcDesc = pspcDesc;
	}

	public List<PropAssets> getPropAssetses() {
		return this.propAssetses;
	}

	public void setPropAssetses(List<PropAssets> propAssetses) {
		this.propAssetses = propAssetses;
	}

	public List<PropServiceProvider> getPropServiceProviders() {
		return this.propServiceProviders;
	}

	public void setPropServiceProviders(List<PropServiceProvider> propServiceProviders) {
		this.propServiceProviders = propServiceProviders;
	}

}
