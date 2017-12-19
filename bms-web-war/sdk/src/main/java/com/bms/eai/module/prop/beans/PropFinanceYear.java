package com.bms.eai.module.prop.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("PropFinanceYear")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true) 
public class PropFinanceYear {

	@JsonProperty("propAnnualReminders")
	private PropAnnualReminders propAnnualReminders;
	
	@JsonProperty("propDetailsMaster")
	private PropDetailsMaster propDetailsMaster;
	
	@JsonProperty("pfyYear")
	private Integer pfyYear;
	
	@JsonProperty("pfyYearStart")
	private Date pfyYearStart;
	
	@JsonProperty("pfyYearEnd")
	private Date pfyYearEnd;
	
	public PropFinanceYear() {
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

	public Integer getPfyYear() {
		return this.pfyYear;
	}

	public void setPfyYear(Integer pfyYear) {
		this.pfyYear = pfyYear;
	}

	public Date getPfyYearStart() {
		return this.pfyYearStart;
	}

	public void setPfyYearStart(Date pfyYearStart) {
		this.pfyYearStart = pfyYearStart;
	}

	public Date getPfyYearEnd() {
		return this.pfyYearEnd;
	}

	public void setPfyYearEnd(Date pfyYearEnd) {
		this.pfyYearEnd = pfyYearEnd;
	}

}
