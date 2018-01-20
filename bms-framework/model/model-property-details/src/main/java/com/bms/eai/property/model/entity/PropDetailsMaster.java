package com.bms.eai.property.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Filter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.StringUtils;

import com.bms.eai.common.model.core.AbstractEntity;
import com.bms.eai.common.model.entity.PropState;
import com.bms.eai.common.model.entity.PropType;
import com.bms.eai.constants.RegExpression;
import com.bms.eai.lib.ObjectUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

 
@SuppressWarnings("serial")
@JsonRootName("propDetailsMaster")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonDeserialize(as = PropState.class)
@Entity
@Table(name = "prop_details_master", uniqueConstraints = @UniqueConstraint(columnNames = "pdm_name"))
public class PropDetailsMaster extends AbstractEntity<PropDetailsMaster,String>  implements java.io.Serializable {

	@JsonProperty("id") 
	@Id
	@Column(name = "pdm_id", length = 50)
	private String id;
	
	@JsonProperty("state") 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ps_id", nullable = false)
	private PropState propState;
	
	@JsonProperty("propType")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pt_id", nullable = false)
	private PropType propType;
	
	@JsonProperty("name")
	@NotEmpty(message="empty.propname")
	@Pattern(regexp=RegExpression.REGEXP_STRRING_SPACE,message="error.str.regex")
	@Size(min = 1, max = 200, message = "error.propname.size")
	@Column(name = "pdm_name", unique = true, nullable = false, length = 250)
	private String name;
	
	@JsonProperty("totalUnits")
	@NotNull(message="empty.totalunits")
	@Min(value=1,message="error.min.value")
	@Max(value=25,message="error.max.value")
	@Column(name = "pdm_total_units", nullable = false)
	private Integer totalUnits;
	
	@JsonProperty("email")
	@Email(message="empty.email")
	@Size(min = 1, max = 150, message = "error.email.size")
	@Column(name = "pdm_email", nullable = false, length = 200)
	private String email;
	
	@JsonProperty("jmbMc")
	@NotEmpty(message="empty.jmbmc")
	@Column(name = "pdm_jmb_mc", length = 50)
	private String jmbMc;
	
	//@JsonProperty("logo")
	@JsonIgnore
	@Lob
	@Column(name = "pdm_logo")
	private byte[] logo;
	
	@JsonProperty("description")
	@Column(name = "pdm_description")
	private String description;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "propDetailsMaster")
	@Filter(name = AbstractEntity.NON_DELETED_FILTER_NAME)
	private List<PropFixedAnualRecurring> propFixedAnualRecurrings ;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "propDetailsMaster")
	@Filter(name = AbstractEntity.NON_DELETED_FILTER_NAME)
	private List<PropParkingBayDetails> propParkingBayDetailses;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "propDetailsMaster")
	@Filter(name = AbstractEntity.NON_DELETED_FILTER_NAME)
	private List<PropFacilities> propFacilitieses;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "propDetailsMaster")
	@Filter(name = AbstractEntity.NON_DELETED_FILTER_NAME)
	private List<PropFinanceYear> propFinanceYears ;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "propDetailsMaster")
	@Filter(name = AbstractEntity.NON_DELETED_FILTER_NAME)
	private List<PropBlockDetails> propBlockDetailses ;
	
	//@NotEmpty(message="empty.state")
	@JsonProperty("stateId")
	@Transient
	private String stateId;
	
	//@NotEmpty(message="empty.proptype")
	@JsonProperty("propTypeId")
	@Transient
	private String propTypeId;
	
	public PropDetailsMaster() {}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public PropState getPropState() {
		return this.propState;
	}

	public void setPropState(PropState propState) {
		this.propState = propState;
	}

	public PropType getPropType() {
		return this.propType;
	}

	public void setPropType(PropType propType) {
		this.propType = propType;
	}
	 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTotalUnits() {
		return totalUnits;
	}

	public void setTotalUnits(Integer totalUnits) {
		this.totalUnits = totalUnits;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJmbMc() {
		return jmbMc;
	}

	public void setJmbMc(String jmbMc) {
		this.jmbMc = jmbMc;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<PropFixedAnualRecurring> getPropFixedAnualRecurrings() {
		return this.propFixedAnualRecurrings;
	}

	public void setPropFixedAnualRecurrings(List<PropFixedAnualRecurring> propFixedAnualRecurrings) {
		this.propFixedAnualRecurrings = propFixedAnualRecurrings;
	}

	public List<PropParkingBayDetails> getPropParkingBayDetailses() {
		return this.propParkingBayDetailses;
	}

	public void setPropParkingBayDetailses(List<PropParkingBayDetails> propParkingBayDetailses) {
		this.propParkingBayDetailses = propParkingBayDetailses;
	}

	public List<PropFacilities> getPropFacilitieses() {
		return this.propFacilitieses;
	}

	public void setPropFacilitieses(List<PropFacilities> propFacilitieses) {
		this.propFacilitieses = propFacilitieses;
	}

	public List<PropFinanceYear> getPropFinanceYears() {
		return this.propFinanceYears;
	}

	public void setPropFinanceYears(List<PropFinanceYear> propFinanceYears) {
		this.propFinanceYears = propFinanceYears;
	}

	public List<PropBlockDetails> getPropBlockDetailses() {
		return this.propBlockDetailses;
	}

	public void setPropBlockDetailses(List<PropBlockDetails> propBlockDetailses) {
		this.propBlockDetailses = propBlockDetailses;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getPropTypeId() {
		return propTypeId;
	}

	public void setPropTypeId(String propTypeId) {
		this.propTypeId = propTypeId;
	}

	@Override
	public String toString() {
		return ObjectUtils.toString(this, true);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	protected void doCopyUpdateFieldsFrom(PropDetailsMaster fromEntity) {
		this.setDescription(StringUtils.hasText(fromEntity.description)?fromEntity.description:this.description);
		this.setEmail(StringUtils.hasText(fromEntity.email)?fromEntity.email:this.email);
		this.setJmbMc(StringUtils.hasText(fromEntity.jmbMc)?fromEntity.jmbMc:this.jmbMc);
		this.setLogo((fromEntity.logo!=null&&fromEntity.logo.length>0)?fromEntity.logo:this.logo);
		this.setName(StringUtils.hasText(fromEntity.name)?fromEntity.name:this.name); 
		this.setPropState(fromEntity.propState!=null?fromEntity.propState:this.propState);
		this.setPropType(fromEntity.propType!=null?fromEntity.propType:this.propType);
		this.setTotalUnits(fromEntity.totalUnits!=null?fromEntity.totalUnits:this.totalUnits);
	}

}
