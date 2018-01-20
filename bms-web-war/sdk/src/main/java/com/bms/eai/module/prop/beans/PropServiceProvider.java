package com.bms.eai.module.prop.beans;

import java.util.Date;
import java.util.List;

import com.bms.eai.module.core.AbstractSdkEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("PropServiceProvider")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true) 
public class PropServiceProvider extends AbstractSdkEntity  {

	@JsonProperty("propAnnualReminders")
	private PropAnnualReminders propAnnualReminders;
	
	@JsonProperty("propContactDetails")
	private PropContactDetails propContactDetails;
	
	@JsonProperty("propDuration")
	private PropDuration propDuration;
	
	@JsonProperty("propServiceProviderCategory")
	private PropServiceProviderCategory propServiceProviderCategory;
	
	@JsonProperty("pspName")
	private String pspName;
	
	@JsonProperty("pspContractual")
	private Boolean pspContractual;
	
	@JsonProperty("pspPersonIncharge")
	private String pspPersonIncharge;
	
	@JsonProperty("pspPassword")
	private String pspPassword;
	
	@JsonProperty("pspContractStart")
	private Date pspContractStart;
	
	@JsonProperty("pspContractEnd")
	private Date pspContractEnd;
	
	@JsonProperty("pspHeadCount")
	private Integer pspHeadCount;
	
	@JsonProperty("pspMonthlyPayment")
	private double pspMonthlyPayment;
	
	@JsonProperty("pspAnnualPayment")
	private double pspAnnualPayment;
	
	@JsonProperty("pspJobScope")
	private String pspJobScope;
	
	@JsonProperty("pspDocuments")
	private byte[] pspDocuments;
	
	@JsonProperty("propAssetses")
	private List<PropAssets> propAssetses;

	public PropServiceProvider() {
	}

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

	public PropDuration getPropDuration() {
		return this.propDuration;
	}

	public void setPropDuration(PropDuration propDuration) {
		this.propDuration = propDuration;
	}

	public PropServiceProviderCategory getPropServiceProviderCategory() {
		return this.propServiceProviderCategory;
	}

	public void setPropServiceProviderCategory(PropServiceProviderCategory propServiceProviderCategory) {
		this.propServiceProviderCategory = propServiceProviderCategory;
	}

	public String getPspName() {
		return this.pspName;
	}

	public void setPspName(String pspName) {
		this.pspName = pspName;
	}

	public Boolean getPspContractual() {
		return this.pspContractual;
	}

	public void setPspContractual(Boolean pspContractual) {
		this.pspContractual = pspContractual;
	}

	public String getPspPersonIncharge() {
		return this.pspPersonIncharge;
	}

	public void setPspPersonIncharge(String pspPersonIncharge) {
		this.pspPersonIncharge = pspPersonIncharge;
	}

	public String getPspPassword() {
		return this.pspPassword;
	}

	public void setPspPassword(String pspPassword) {
		this.pspPassword = pspPassword;
	}

	public Date getPspContractStart() {
		return this.pspContractStart;
	}

	public void setPspContractStart(Date pspContractStart) {
		this.pspContractStart = pspContractStart;
	}

	public Date getPspContractEnd() {
		return this.pspContractEnd;
	}

	public void setPspContractEnd(Date pspContractEnd) {
		this.pspContractEnd = pspContractEnd;
	}

	public Integer getPspHeadCount() {
		return this.pspHeadCount;
	}

	public void setPspHeadCount(Integer pspHeadCount) {
		this.pspHeadCount = pspHeadCount;
	}

	public double getPspMonthlyPayment() {
		return this.pspMonthlyPayment;
	}

	public void setPspMonthlyPayment(double pspMonthlyPayment) {
		this.pspMonthlyPayment = pspMonthlyPayment;
	}

	public double getPspAnnualPayment() {
		return this.pspAnnualPayment;
	}

	public void setPspAnnualPayment(double pspAnnualPayment) {
		this.pspAnnualPayment = pspAnnualPayment;
	}

	public String getPspJobScope() {
		return this.pspJobScope;
	}

	public void setPspJobScope(String pspJobScope) {
		this.pspJobScope = pspJobScope;
	}

	public byte[] getPspDocuments() {
		return this.pspDocuments;
	}

	public void setPspDocuments(byte[] pspDocuments) {
		this.pspDocuments = pspDocuments;
	}

	public List<PropAssets> getPropAssetses() {
		return this.propAssetses;
	}

	public void setPropAssetses(List<PropAssets> propAssetses) {
		this.propAssetses = propAssetses;
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
