package com.bms.eai.module.prop.beans;

import com.bms.eai.module.core.AbstractSdkEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("PropParkingBayDetails")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true) 
public class PropParkingBayDetails extends AbstractSdkEntity {

	@JsonProperty("propBlockDetails")
	private PropBlockDetails propBlockDetails;
	
	@JsonProperty("propDetailsMaster")
	private PropDetailsMaster propDetailsMaster;
	
	@JsonProperty("ppbdFloor")
	private int ppbdFloor;
	
	@JsonProperty("ppbdNoBays")
	private int ppbdNoBays;
	
	@JsonProperty("propAnnualReminders")
	private String ppbdDesc;
	

	public PropParkingBayDetails() {
	}
	 
	public PropBlockDetails getPropBlockDetails() {
		return this.propBlockDetails;
	}

	public void setPropBlockDetails(PropBlockDetails propBlockDetails) {
		this.propBlockDetails = propBlockDetails;
	}

	public PropDetailsMaster getPropDetailsMaster() {
		return this.propDetailsMaster;
	}

	public void setPropDetailsMaster(PropDetailsMaster propDetailsMaster) {
		this.propDetailsMaster = propDetailsMaster;
	}

	public int getPpbdFloor() {
		return this.ppbdFloor;
	}

	public void setPpbdFloor(int ppbdFloor) {
		this.ppbdFloor = ppbdFloor;
	}

	public int getPpbdNoBays() {
		return this.ppbdNoBays;
	}

	public void setPpbdNoBays(int ppbdNoBays) {
		this.ppbdNoBays = ppbdNoBays;
	}

	public String getPpbdDesc() {
		return this.ppbdDesc;
	}

	public void setPpbdDesc(String ppbdDesc) {
		this.ppbdDesc = ppbdDesc;
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
