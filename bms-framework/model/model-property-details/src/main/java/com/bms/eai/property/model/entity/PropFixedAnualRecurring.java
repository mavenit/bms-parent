package com.bms.eai.property.model.entity;

import java.util.Date;

import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.StringUtils;

import com.bms.eai.common.model.core.AbstractEntity;
import com.bms.eai.lib.JsonDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("serial")
@JsonRootName("fixedAnualRecurring")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "prop_fixed_anual_recurring")
public class PropFixedAnualRecurring extends AbstractEntity<PropFixedAnualRecurring,String>  implements java.io.Serializable {

	//AuditingEntityListener a;
	
	@JsonIgnore
	@Id
	@Column(name = "pfar_id", length = 45)
	private String id;
	
	@JsonProperty("annualReminders")
	@ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name = "par_id", nullable = false)
	@NotFound(action=NotFoundAction.IGNORE)
	private PropAnnualReminders propAnnualReminders;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name = "pdm_id")
	private PropDetailsMaster propDetailsMaster;
	
	@JsonProperty("desc")
	@NotEmpty(message="empty.desc")
	@Size(min = 10, max = 200, message = "error.desc.size")
	//@ColumnResult(name="id")
	@Column(name = "pfar_description", nullable = false, length = 250)
	private String desc;
	
	@JsonProperty("expiryDate")
	@JsonSerialize(using=JsonDateSerializer.class)
	@NotNull(message="empty.expiryDate")
	@Temporal(TemporalType.DATE)
	@Column(name = "pfar_expiry_date", nullable = false, length = 10)
	private Date expiryDate;
	
	@JsonProperty("annualRemindersId")
	@Transient
	private String annualRemindersId;
	
	@JsonProperty("propDetailsMasterId")
	@Transient
	private String propDetailsMasterId;

	public PropFixedAnualRecurring() {}
	 
	@Override
	protected void doCopyUpdateFieldsFrom(PropFixedAnualRecurring fromEntity) {
		this.desc = StringUtils.hasText(fromEntity.desc) ? fromEntity.desc : this.desc;
		this.expiryDate =  fromEntity.expiryDate!=null ? fromEntity.expiryDate :this.expiryDate;
		fromEntity.propAnnualReminders.doCopyUpdateFieldsFrom(fromEntity.propAnnualReminders); 
		//this.propAnnualReminders =fromEntity.propAnnualReminders!=null ? fromEntity.propAnnualReminders :this.propAnnualReminders;
		this.propDetailsMaster =  fromEntity.propDetailsMaster!=null ? fromEntity.propDetailsMaster :this.propDetailsMaster;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PropAnnualReminders getPropAnnualReminders() {
		return propAnnualReminders;
	}

	public void setPropAnnualReminders(PropAnnualReminders propAnnualReminders) {
		this.propAnnualReminders = propAnnualReminders;
	}

	public PropDetailsMaster getPropDetailsMaster() {
		return propDetailsMaster;
	}

	public void setPropDetailsMaster(PropDetailsMaster propDetailsMaster) {
		this.propDetailsMaster = propDetailsMaster;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
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

}
