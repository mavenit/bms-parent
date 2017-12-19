package com.bms.eai.property.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Filter;

import com.bms.eai.common.model.core.AbstractEntity;
import com.bms.eai.lib.JsonTimeSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("serial")
@JsonRootName("annualReminders")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "prop_annual_reminders", uniqueConstraints = @UniqueConstraint(columnNames = "par_days"))
public class PropAnnualReminders extends AbstractEntity<PropAnnualReminders,String> implements java.io.Serializable {

	@JsonIgnore
	@Id
	@Column(name = "par_id", length = 45)
	private String id;
	
	@JsonProperty("days")
	@NotNull(message="empty.days")
	@Min(value=1,message="error.min.value")
	@Max(value=31,message="error.max.value")
	@Column(name = "par_days", unique = true, nullable = false)
	private Integer days;
	
	@Temporal(TemporalType.TIME)
	@Column(name = "par_time", length = 8)
	private Date parTime;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "propAnnualReminders")
	@Filter(name = AbstractEntity.NON_DELETED_FILTER_NAME)
	private List<PropAssets> propAssetses ;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "propAnnualReminders")
	@Filter(name = AbstractEntity.NON_DELETED_FILTER_NAME)
	private List<PropFixedAnualRecurring> propFixedAnualRecurrings ;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "propAnnualReminders")
	@Filter(name = AbstractEntity.NON_DELETED_FILTER_NAME)
	private List<PropFinanceYear> propFinanceYears ;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "propAnnualReminders")
	@Filter(name = AbstractEntity.NON_DELETED_FILTER_NAME)
	private List<PropServiceProvider> propServiceProviders ;
	
	@JsonProperty("time")
	@JsonSerialize(using=JsonTimeSerializer.class)
	@Transient
	private String time;
	
	public PropAnnualReminders() {}

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Date getParTime() {
		return parTime;
	}

	public void setParTime(Date parTime) {
		this.parTime = parTime;
	}

	@Override
	protected void doCopyUpdateFieldsFrom(PropAnnualReminders fromEntity) {
		this.days = fromEntity.days!=null ? fromEntity.days : this.days;
		this.parTime = fromEntity!=null ? fromEntity.parTime : this.parTime;
		/*this.propAssetses = fromEntity.propAssetses!=null ? fromEntity.propAssetses : this.propAssetses ;
		this.propFinanceYears = fromEntity.propFinanceYears!=null ? fromEntity.propFinanceYears : this.propFinanceYears;
		this.propFixedAnualRecurrings = fromEntity.propFixedAnualRecurrings!=null ? fromEntity.propFixedAnualRecurrings : this.propFixedAnualRecurrings ;
		this.propServiceProviders = fromEntity.propServiceProviders!=null ? fromEntity.propServiceProviders : this.propServiceProviders;*/
	}

}
