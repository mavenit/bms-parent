package com.bms.eai.module.prop.beans;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("PropAnnualReminders")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropAnnualReminders   {

	@JsonProperty("parDays")
	private int parDays;
	
	@JsonProperty("parTime")
	private Date parTime;
	
	@JsonProperty("propAssetses")
	private List<PropAssets> propAssetses ;
	
	@JsonProperty("propFixedAnualRecurrings")
	private List<PropFixedAnualRecurring> propFixedAnualRecurrings ;
	
	@JsonProperty("propFinanceYears")
	private List<PropFinanceYear> propFinanceYears ;
	
	@JsonProperty("propServiceProviders")
	private List<PropServiceProvider> propServiceProviders ;

	public PropAnnualReminders() {}

	public int getParDays() {
		return this.parDays;
	}

	public void setParDays(int parDays) {
		this.parDays = parDays;
	}
	 
	public Date getParTime() {
		return this.parTime;
	}

	public void setParTime(Date parTime) {
		this.parTime = parTime;
	}

	public List<PropAssets> getPropAssetses() {
		return this.propAssetses;
	}

	public void setPropAssetses(List<PropAssets> propAssetses) {
		this.propAssetses = propAssetses;
	}

	public List<PropFixedAnualRecurring> getPropFixedAnualRecurrings() {
		return this.propFixedAnualRecurrings;
	}

	public void setPropFixedAnualRecurrings(List<PropFixedAnualRecurring> propFixedAnualRecurrings) {
		this.propFixedAnualRecurrings = propFixedAnualRecurrings;
	}
	
	public List<PropFinanceYear> getPropFinanceYears() {
		return this.propFinanceYears;
	}

	public void setPropFinanceYears(List<PropFinanceYear> propFinanceYears) {
		this.propFinanceYears = propFinanceYears;
	}

	public List<PropServiceProvider> getPropServiceProviders() {
		return this.propServiceProviders;
	}

	public void setPropServiceProviders(List<PropServiceProvider> propServiceProviders) {
		this.propServiceProviders = propServiceProviders;
	}

}
