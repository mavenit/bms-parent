package com.bms.eai.property.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Filter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.StringUtils;

import com.bms.eai.common.model.core.AbstractEntity;
import com.bms.eai.constants.RegExpression;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

 
@SuppressWarnings("serial")
@JsonRootName("serviceProvider")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "prop_service_provider",uniqueConstraints = @UniqueConstraint(columnNames = "psp_name"))
public class PropServiceProvider extends AbstractEntity<PropServiceProvider,String>  implements java.io.Serializable {

	@JsonIgnore
	@Id
	@Column(name = "psp_id",  length = 45)
	private String id;
	
	@JsonProperty("annualReminders")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "par_id")
	private PropAnnualReminders propAnnualReminders;
	
	@JsonProperty("contactDetails")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pcd_id", nullable = false)
	private PropContactDetails propContactDetails;
	
	@JsonProperty("duration")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pd_id")
	private PropDuration propDuration;
	
	@JsonProperty("serviceProviderCategory")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pspc_id", nullable = false)
	private PropServiceProviderCategory propServiceProviderCategory;
	
	@JsonProperty("name")
	@NotEmpty(message="empty.name")
	@Pattern(regexp=RegExpression.REGEXP_STRRING_SPACE,message="error.str.regex")
	@Size(min = 1, max = 200, message = "error.name.size")
	@Column(name = "psp_name", unique = true, nullable = false, length = 250)
	private String name;
	
	@JsonProperty("contractual")
	@NotNull(message="empty.contractual")
	@Column(name = "psp_contractual")
	private Boolean contractual;
	
	@JsonProperty("personIncharge")
	@NotEmpty(message="empty.personIncharge")
	@Pattern(regexp=RegExpression.REGEXP_STRRING_SPACE,message="error.str.regex")
	@Size(min = 1, max = 200, message = "error.personIncharge.size")
	@Column(name = "psp_person_incharge", nullable = false, length = 250)
	private String personIncharge;
	
	@JsonProperty("password")
	@NotEmpty(message="empty.password")
	@Pattern(regexp=RegExpression.REGEXP_STRRING,message="error.str.regex")
	@Size(min = 1, max = 200, message = "error.password.size")
	@Column(name = "psp_password")
	private String password;
	
	@JsonProperty("contractStart")
	@NotEmpty(message="empty.contractStart")
	@Size(min = 10, max = 10, message = "error.contractStart.size")
	@Temporal(TemporalType.DATE)
	@Column(name = "psp_contract_start", nullable = false, length = 10)
	private Date contractStart;
	
	@JsonProperty("contractEnd")
	@NotEmpty(message="empty.contractEnd")
	@Size(min = 10, max = 10, message = "error.contractEnd.size")
	@Temporal(TemporalType.DATE)
	@Column(name = "psp_contract_end", nullable = false, length = 10)
	private Date contractEnd;
	
	@JsonProperty("headCount")
	@NotEmpty(message="empty.headCount")
	@Size(min = 1, max = 10, message = "error.headCount.size")
	@Column(name = "psp_head_count")
	private Integer headCount;
	
	@JsonProperty("monthlyPayment")
	@NotEmpty(message="empty.monthlyPayment")
	@Column(name = "psp_monthly_payment", nullable = false, precision = 22, scale = 0)
	private Double monthlyPayment;
	
	@JsonProperty("annualPayment")
	@NotEmpty(message="empty.annualPayment")
	@Column(name = "psp_annual_payment", nullable = false, precision = 22, scale = 0)
	private Double annualPayment;
	
	@JsonProperty("jobScope")
	@NotEmpty(message="empty.jobScope")
	@Size(min = 0, max = 200, message = "error.jobScope.size")
	@Column(name = "psp_job_scope")
	private String jobScope;
	
	/*@Lob
	@JsonIgnore
	@Column(name = "psp_documents", length = 8000000)
	private byte[] pspDocuments;*/
	
	@JsonIgnore
	@OneToMany(mappedBy = "propServiceProvider",cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@Filter(name = AbstractEntity.NON_DELETED_FILTER_NAME)
	private List<PropAssets> propAssetses;
	
	@JsonProperty("annualRemindersId")
	@NotNull(message="empty.annualRemindersId")
	@Transient
	private String annualRemindersId;

	@JsonProperty("contactDetailsId")
	@NotNull(message="empty.contactDetailsId")
	@Transient
	private String contactDetailsId;
	
	@JsonProperty("durationId")
	@NotNull(message="empty.durationId")
	@Transient
	private String durationId;
	
	@JsonProperty("serviceProviderCategoryId")
	@NotNull(message="empty.serviceProviderCategoryId")
	@Transient
	private String serviceProviderCategoryId;
	
	@Override
	protected void doCopyUpdateFieldsFrom(PropServiceProvider fromEntity) {
		this.annualPayment = fromEntity.annualPayment!=null ? fromEntity.annualPayment :this.annualPayment;
		this.monthlyPayment = fromEntity.monthlyPayment!=null ? fromEntity.monthlyPayment :this.monthlyPayment;
		this.contractEnd = fromEntity.contractEnd!=null ? fromEntity.contractEnd :this.contractEnd;
		this.contractStart = fromEntity.contractStart!=null ? fromEntity.contractStart :this.contractStart;
		this.contractual = fromEntity.contractual!=null ? fromEntity.contractual :this.contractual;
		this.headCount = fromEntity.headCount!=null ? fromEntity.headCount :this.headCount;
		this.jobScope = StringUtils.hasText(fromEntity.jobScope) ? fromEntity.jobScope : this.jobScope;
		this.name = StringUtils.hasText(fromEntity.name) ? fromEntity.name : this.name;
		this.password = StringUtils.hasText(fromEntity.password) ? fromEntity.password : this.password;
		this.personIncharge = StringUtils.hasText(fromEntity.personIncharge) ? fromEntity.personIncharge : this.personIncharge;
		this.propAnnualReminders = fromEntity.propAnnualReminders!=null ? fromEntity.propAnnualReminders :this.propAnnualReminders;
		this.propServiceProviderCategory= fromEntity.propServiceProviderCategory!=null ? fromEntity.propServiceProviderCategory :this.propServiceProviderCategory;
		this.propDuration =  fromEntity.propDuration!=null ? fromEntity.propDuration :this.propDuration;
		this.propContactDetails = fromEntity.propContactDetails!=null ? fromEntity.propContactDetails :this.propContactDetails;
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

	public PropContactDetails getPropContactDetails() {
		return propContactDetails;
	}

	public void setPropContactDetails(PropContactDetails propContactDetails) {
		this.propContactDetails = propContactDetails;
	}

	public PropDuration getPropDuration() {
		return propDuration;
	}

	public void setPropDuration(PropDuration propDuration) {
		this.propDuration = propDuration;
	}

	public PropServiceProviderCategory getPropServiceProviderCategory() {
		return propServiceProviderCategory;
	}

	public void setPropServiceProviderCategory(PropServiceProviderCategory propServiceProviderCategory) {
		this.propServiceProviderCategory = propServiceProviderCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getContractual() {
		return contractual;
	}

	public void setContractual(Boolean contractual) {
		this.contractual = contractual;
	}

	public String getPersonIncharge() {
		return personIncharge;
	}

	public void setPersonIncharge(String personIncharge) {
		this.personIncharge = personIncharge;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getContractStart() {
		return contractStart;
	}

	public void setContractStart(Date contractStart) {
		this.contractStart = contractStart;
	}

	public Date getContractEnd() {
		return contractEnd;
	}

	public void setContractEnd(Date contractEnd) {
		this.contractEnd = contractEnd;
	}

	public Integer getHeadCount() {
		return headCount;
	}

	public void setHeadCount(Integer headCount) {
		this.headCount = headCount;
	}

	public Double getMonthlyPayment() {
		return monthlyPayment;
	}

	public void setMonthlyPayment(Double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}

	public Double getAnnualPayment() {
		return annualPayment;
	}

	public void setAnnualPayment(Double annualPayment) {
		this.annualPayment = annualPayment;
	}

	public String getJobScope() {
		return jobScope;
	}

	public void setJobScope(String jobScope) {
		this.jobScope = jobScope;
	}

	public List<PropAssets> getPropAssetses() {
		return propAssetses;
	}

	public void setPropAssetses(List<PropAssets> propAssetses) {
		this.propAssetses = propAssetses;
	}

	public String getAnnualRemindersId() {
		return annualRemindersId;
	}

	public void setAnnualRemindersId(String annualRemindersId) {
		this.annualRemindersId = annualRemindersId;
	}

	public String getContactDetailsId() {
		return contactDetailsId;
	}

	public void setContactDetailsId(String contactDetailsId) {
		this.contactDetailsId = contactDetailsId;
	}

	public String getDurationId() {
		return durationId;
	}

	public void setDurationId(String durationId) {
		this.durationId = durationId;
	}

	public String getServiceProviderCategoryId() {
		return serviceProviderCategoryId;
	}

	public void setServiceProviderCategoryId(String serviceProviderCategoryId) {
		this.serviceProviderCategoryId = serviceProviderCategoryId;
	}

}
