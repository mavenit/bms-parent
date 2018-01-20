package com.bms.eai.module.prop.beans;

import java.util.Date;

import com.bms.eai.module.core.AbstractSdkEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("PropFixedAnualRecurring")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true) 
public class PropFixedAnualRecurring extends AbstractSdkEntity  {

	@JsonProperty("propAnnualReminders")
	private PropAnnualReminders propAnnualReminders;
	
	@JsonProperty("propDetailsMaster")
	private PropDetailsMaster propDetailsMaster;
	
	@JsonProperty("pfarDescription")
	private String pfarDescription;
	
	@JsonProperty("pfarExpiryDate")
	private Date pfarExpiryDate;

	public PropFixedAnualRecurring() {
	}
	 
	public PropAnnualReminders getPropAnnualReminders() {
		return this.propAnnualReminders;
	}

	public void setPropAnnualReminders(PropAnnualReminders propAnnualReminders) {
		this.propAnnualReminders = propAnnualReminders;
	}

	public PropDetailsMaster getPropDetailsMaster() {
		return this.propDetailsMaster;
	}

	public void setPropDetailsMaster(PropDetailsMaster propDetailsMaster) {
		this.propDetailsMaster = propDetailsMaster;
	}

	public String getPfarDescription() {
		return this.pfarDescription;
	}

	public void setPfarDescription(String pfarDescription) {
		this.pfarDescription = pfarDescription;
	}

	public Date getPfarExpiryDate() {
		return this.pfarExpiryDate;
	}

	public void setPfarExpiryDate(Date pfarExpiryDate) {
		this.pfarExpiryDate = pfarExpiryDate;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}

}
