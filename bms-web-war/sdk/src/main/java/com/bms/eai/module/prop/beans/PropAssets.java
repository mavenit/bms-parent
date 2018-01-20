package com.bms.eai.module.prop.beans;

import java.util.Date;

import com.bms.eai.module.core.AbstractSdkEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("PropAssets")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropAssets extends AbstractSdkEntity  {

	@JsonProperty("paName")
	private String paName;
	
	@JsonProperty("pacId")
	private String pacId;
	
	@JsonProperty("paDesc")
	private String paDesc;
	
	@JsonProperty("paManufacturer")
	private String paManufacturer;
	
	@JsonProperty("paLocation")
	private String paLocation;
	
	@JsonProperty("paSerialNo")
	private String paSerialNo;
	
	@JsonProperty("paPurchaseDate")
	private Date paPurchaseDate;
	
	@JsonProperty("paWarrantyStart")
	private Date paWarrantyStart;
	
	@JsonProperty("paWarrantyEnd")
	private Date paWarrantyEnd;
	
	@JsonProperty("parPersonIncharge")
	private String parPersonIncharge;
	
	@JsonProperty("propAnnualReminders")
	private PropAnnualReminders propAnnualReminders;
	
	@JsonProperty("propContactDetails")
	private PropContactDetails propContactDetails;
	
	@JsonProperty("propServiceProvider")
	private PropServiceProvider propServiceProvider;
	
	@JsonProperty("propServiceProviderCategory")
	private PropServiceProviderCategory propServiceProviderCategory;
	
	public PropAssets() {}

	public PropAnnualReminders getPropAnnualReminders() {
		return this.propAnnualReminders;
	}

	public void setPropAnnualReminders(PropAnnualReminders propAnnualReminders) {
		this.propAnnualReminders = propAnnualReminders;
	}

	public PropContactDetails getPropContactDetails() {
		return this.propContactDetails;
	}

	public void setPropContactDetails(PropContactDetails propContactDetails) {
		this.propContactDetails = propContactDetails;
	}

	public PropServiceProvider getPropServiceProvider() {
		return this.propServiceProvider;
	}

	public void setPropServiceProvider(PropServiceProvider propServiceProvider) {
		this.propServiceProvider = propServiceProvider;
	}

	public PropServiceProviderCategory getPropServiceProviderCategory() {
		return this.propServiceProviderCategory;
	}

	public void setPropServiceProviderCategory(PropServiceProviderCategory propServiceProviderCategory) {
		this.propServiceProviderCategory = propServiceProviderCategory;
	}

	public String getPaName() {
		return this.paName;
	}

	public void setPaName(String paName) {
		this.paName = paName;
	}

	public String getPacId() {
		return this.pacId;
	}

	public void setPacId(String pacId) {
		this.pacId = pacId;
	}

	public String getPaDesc() {
		return this.paDesc;
	}

	public void setPaDesc(String paDesc) {
		this.paDesc = paDesc;
	}

	public String getPaManufacturer() {
		return this.paManufacturer;
	}

	public void setPaManufacturer(String paManufacturer) {
		this.paManufacturer = paManufacturer;
	}

	public String getPaLocation() {
		return this.paLocation;
	}

	public void setPaLocation(String paLocation) {
		this.paLocation = paLocation;
	}

	public String getPaSerialNo() {
		return this.paSerialNo;
	}

	public void setPaSerialNo(String paSerialNo) {
		this.paSerialNo = paSerialNo;
	}

	public Date getPaPurchaseDate() {
		return this.paPurchaseDate;
	}

	public void setPaPurchaseDate(Date paPurchaseDate) {
		this.paPurchaseDate = paPurchaseDate;
	}

	public Date getPaWarrantyStart() {
		return this.paWarrantyStart;
	}

	public void setPaWarrantyStart(Date paWarrantyStart) {
		this.paWarrantyStart = paWarrantyStart;
	}

	public Date getPaWarrantyEnd() {
		return this.paWarrantyEnd;
	}

	public void setPaWarrantyEnd(Date paWarrantyEnd) {
		this.paWarrantyEnd = paWarrantyEnd;
	}

	public String getParPersonIncharge() {
		return this.parPersonIncharge;
	}

	public void setParPersonIncharge(String parPersonIncharge) {
		this.parPersonIncharge = parPersonIncharge;
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
