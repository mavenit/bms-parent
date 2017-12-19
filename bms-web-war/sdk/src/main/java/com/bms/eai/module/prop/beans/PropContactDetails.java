package com.bms.eai.module.prop.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("PropContactDetails")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropContactDetails  {

	@JsonProperty("pcdAddressLine1")
	private String pcdAddressLine1;
	
	@JsonProperty("pcdAddressLine2")
	private String pcdAddressLine2;
	
	@JsonProperty("pcdPoscode")
	private String pcdPoscode;
	
	@JsonProperty("pcdCity")
	private String pcdCity;
	
	@JsonProperty("pcdOfficePhno")
	private String pcdOfficePhno;
	
	@JsonProperty("pcdOfficeFaxno")
	private String pcdOfficeFaxno;
	
	@JsonProperty("pcdMobileno")
	private String pcdMobileno;
	
	@JsonProperty("pcdEmailid")
	private String pcdEmailid;
	
	@JsonProperty("propServiceProviders")
	private List<PropServiceProvider> propServiceProviders ;
	
	@JsonProperty("propAssetses")
	private List<PropAssets> propAssetses ;

	public PropContactDetails() {}


	public String getPcdAddressLine1() {
		return this.pcdAddressLine1;
	}

	public void setPcdAddressLine1(String pcdAddressLine1) {
		this.pcdAddressLine1 = pcdAddressLine1;
	}

	public String getPcdAddressLine2() {
		return this.pcdAddressLine2;
	}

	public void setPcdAddressLine2(String pcdAddressLine2) {
		this.pcdAddressLine2 = pcdAddressLine2;
	}

	public String getPcdPoscode() {
		return this.pcdPoscode;
	}

	public void setPcdPoscode(String pcdPoscode) {
		this.pcdPoscode = pcdPoscode;
	}

	public String getPcdCity() {
		return this.pcdCity;
	}

	public void setPcdCity(String pcdCity) {
		this.pcdCity = pcdCity;
	}

	public String getPcdOfficePhno() {
		return this.pcdOfficePhno;
	}

	public void setPcdOfficePhno(String pcdOfficePhno) {
		this.pcdOfficePhno = pcdOfficePhno;
	}

	public String getPcdOfficeFaxno() {
		return this.pcdOfficeFaxno;
	}

	public void setPcdOfficeFaxno(String pcdOfficeFaxno) {
		this.pcdOfficeFaxno = pcdOfficeFaxno;
	}

	public String getPcdMobileno() {
		return this.pcdMobileno;
	}

	public void setPcdMobileno(String pcdMobileno) {
		this.pcdMobileno = pcdMobileno;
	}

	public String getPcdEmailid() {
		return this.pcdEmailid;
	}

	public void setPcdEmailid(String pcdEmailid) {
		this.pcdEmailid = pcdEmailid;
	}

	public List<PropServiceProvider> getPropServiceProviders() {
		return this.propServiceProviders;
	}

	public void setPropServiceProviders(List<PropServiceProvider> propServiceProviders) {
		this.propServiceProviders = propServiceProviders;
	}

	public List<PropAssets> getPropAssetses() {
		return this.propAssetses;
	}

	public void setPropAssetses(List<PropAssets> propAssetses) {
		this.propAssetses = propAssetses;
	}

}
