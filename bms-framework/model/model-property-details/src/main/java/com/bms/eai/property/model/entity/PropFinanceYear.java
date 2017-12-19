package com.bms.eai.property.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.bms.eai.common.model.core.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

 
@SuppressWarnings("serial")
@JsonRootName("financeYear")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "prop_finance_year")
public class PropFinanceYear extends AbstractEntity<PropFinanceYear,String>  implements java.io.Serializable {

	@JsonIgnore
	@Id
	@Column(name = "pfy_id", length = 45)
	private String id;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "par_id")
	private PropAnnualReminders propAnnualReminders;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pdm_id")
	private PropDetailsMaster propDetailsMaster;
	
	@JsonProperty("year")
	@NotNull(message="empty.year")
	@Min(value=1,message="error.min.value")
	@Max(value=31,message="error.max.value")
	@Column(name = "pfy_year")
	private Integer year;
	
	@JsonProperty("yearStart")
	@NotEmpty(message="empty.yearStart")
	@Size(min = 10, max = 10, message = "error.yearStart.size")
	@Temporal(TemporalType.DATE)
	@Column(name = "pfy_year_start", length = 10)
	private Date yearStart;
	
	@JsonProperty("yearEnd")
	@NotEmpty(message="empty.yearEnd")
	@Size(min = 10, max = 10, message = "error.yearEnd.size")
	@Temporal(TemporalType.DATE)
	@Column(name = "pfy_year_end", length = 10)
	private Date yearEnd;
	
	@JsonProperty("annualRemindersId")
	@NotNull(message="empty.annualRemindersId")
	@Transient
	private String annualRemindersId;
	
	@JsonProperty("propDetailsMasterId")
	@NotNull(message="empty.propDetailsMasterId")
	@Transient
	private String propDetailsMasterId;
	
	public PropFinanceYear() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Date getYearStart() {
		return yearStart;
	}

	public void setYearStart(Date yearStart) {
		this.yearStart = yearStart;
	}

	public Date getYearEnd() {
		return yearEnd;
	}

	public void setYearEnd(Date yearEnd) {
		this.yearEnd = yearEnd;
	}

	public String getAnnualRemindersId() {
		return annualRemindersId;
	}

	public void setAnnualRemindersId(String annualRemindersId) {
		this.annualRemindersId = annualRemindersId;
	}

	public String getPropDetailsMasterId() {
		return propDetailsMasterId;
	}

	public void setPropDetailsMasterId(String propDetailsMasterId) {
		this.propDetailsMasterId = propDetailsMasterId;
	}

	@Override
	protected void doCopyUpdateFieldsFrom(PropFinanceYear fromEntity) {
		this.propAnnualReminders = fromEntity.propAnnualReminders!=null ? fromEntity.propAnnualReminders : this.propAnnualReminders;
		this.propDetailsMaster = fromEntity.propDetailsMaster!=null ? fromEntity.propDetailsMaster : this.propDetailsMaster;
		this.year = fromEntity.year!=null ? fromEntity.year : this.year ;
		this.yearStart = fromEntity.yearStart!=null ? fromEntity.yearStart : this.yearStart ;
		this.yearEnd = fromEntity.yearEnd!=null ? fromEntity.yearEnd : this.yearEnd;
	}

}
