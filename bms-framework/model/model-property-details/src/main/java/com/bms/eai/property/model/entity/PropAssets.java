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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
@JsonRootName("assets")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "prop_assets", uniqueConstraints = {
		@UniqueConstraint(columnNames = "pa_name"), @UniqueConstraint(columnNames = "pa_serial_no") })
public class PropAssets extends AbstractEntity<PropAssets,String>  implements java.io.Serializable {

	@JsonIgnore
	@Id
	@Column(name = "pa_id", length = 45)
	private String id;
	
	@JsonProperty("name")
	@NotEmpty(message="empty.name")
	@Pattern(regexp=RegExpression.REGEXP_STRRING_SPACE,message="error.str.regex")
	@Size(min = 1, max = 200, message = "error.name.size")
	@Column(name = "pa_name", unique = true, nullable = false, length = 250)
	private String name;
	
	@JsonProperty("desc")
	@NotEmpty(message="empty.desc")
	@Pattern(regexp=RegExpression.REGEXP_STRRING_SPACE,message="error.str.regex")
	@Size(min = 1, max = 300, message = "error.desc.size")
	@Column(name = "pa_desc")
	private String desc;
	
	@JsonProperty("manufacturer")
	@NotEmpty(message="empty.manufacturer")
	@Pattern(regexp=RegExpression.REGEXP_STRRING_SPACE,message="error.str.regex")
	@Size(min = 1, max = 200, message = "error.manufacturer.size")
	@Column(name = "pa_manufacturer", length = 250)
	private String manufacturer;
	
	@JsonProperty("location")
	@NotEmpty(message="empty.location")
	@Pattern(regexp=RegExpression.REGEXP_STRRING_SPACE,message="error.str.regex")
	@Size(min = 1, max = 200, message = "error.location.size")
	@Column(name = "pa_location", length = 250)
	private String location;
	
	@JsonProperty("serialno")
	@NotEmpty(message="empty.serialno")
	@Pattern(regexp=RegExpression.REGEXP_STRRING_SPACE,message="error.str.regex")
	@Size(min = 1, max = 200, message = "error.serialno.size")
	@Column(name = "pa_serial_no", unique = true, nullable = false, length = 250)
	private String serialNo;
	
	@JsonProperty("purchaseDate")
	@NotNull(message="empty.purchaseDate")
	@Size(min = 10, max = 10, message = "error.purchaseDate.size")
	@Temporal(TemporalType.DATE)
	@Column(name = "pa_purchase_date", length = 10)
	private Date purchaseDate;
	
	@JsonProperty("warrantyStart")
	@NotNull(message="empty.warrantyStart")
	@Size(min = 10, max = 10, message = "error.warrantyStart.size")
	@Temporal(TemporalType.DATE)
	@Column(name = "pa_warranty_start", length = 10)
	private Date warrantyStart;
	
	@JsonProperty("warrantyEnd")
	@NotNull(message="empty.warrantyEnd")
	@Size(min = 10, max = 10, message = "error.warrantyEnd.size")
	@Temporal(TemporalType.DATE)
	@Column(name = "pa_warranty_end", length = 10)
	private Date warrantyEnd;
	
	@JsonProperty("personIncharge")
	@NotEmpty(message="empty.personIncharge")
	@Pattern(regexp=RegExpression.REGEXP_STRRING_SPACE,message="error.str.regex")
	@Size(min = 1, max = 200, message = "error.personIncharge.size")
	@Column(name = "par_person_incharge", length = 250)
	private String personIncharge;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pac_id")
	private PropAssetsCategory propAssetsCategory;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "psp_id")
	private PropServiceProvider propServiceProvider;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pcd_id")
	private PropContactDetails propContactDetails;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "par_id")
	private PropAnnualReminders propAnnualReminders;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pspc_id")
	private PropServiceProviderCategory propServiceProviderCategory;
	
	@JsonProperty("serviceProviderCategoryId")
	@NotNull(message="empty.serviceProviderCategoryId")
	@Transient
	private String serviceProviderCategoryId;
	
	@JsonProperty("annualRemindersId")
	@NotNull(message="empty.annualRemindersId")
	@Transient
	private String annualRemindersId;
	
	@JsonProperty("contactDetailsId")
	@NotNull(message="empty.contactDetailsId")
	@Transient
	private String contactDetailsId;
	
	@JsonProperty("assetsCategoryId")
	@NotNull(message="empty.assetsCategoryId")
	@Transient
	private String assetsCategoryId;
	
	@JsonProperty("serviceProviderId")
	@NotNull(message="empty.serviceProviderId")
	@Transient
	private String serviceProviderId;
	
	public PropAssets() {}

	@Override
	protected void doCopyUpdateFieldsFrom(PropAssets fromEntity) {
		this.desc = StringUtils.hasText(fromEntity.desc) ? fromEntity.desc : this.desc;
		this.location = StringUtils.hasText(fromEntity.location) ? fromEntity.location : this.location;
		this.manufacturer = StringUtils.hasText(fromEntity.manufacturer) ? fromEntity.manufacturer : this.manufacturer;
		this.name = StringUtils.hasText(fromEntity.name) ? fromEntity.name : this.name;
		this.personIncharge = StringUtils.hasText(fromEntity.personIncharge) ? fromEntity.personIncharge : this.personIncharge;
		this.serialNo = StringUtils.hasText(fromEntity.serialNo) ? fromEntity.serialNo : this.serialNo;
		this.warrantyStart = fromEntity.warrantyStart!=null ? fromEntity.warrantyStart :this.warrantyStart;
		this.warrantyEnd = fromEntity.warrantyEnd!=null ? fromEntity.warrantyEnd :this.warrantyEnd;
		this.purchaseDate = fromEntity.purchaseDate!=null ? fromEntity.purchaseDate :this.purchaseDate;
		this.propAnnualReminders = fromEntity.propAnnualReminders!=null ? fromEntity.propAnnualReminders :this.propAnnualReminders;
		this.propAssetsCategory = fromEntity.propAssetsCategory!=null ? fromEntity.propAssetsCategory :this.propAssetsCategory;
		this.propContactDetails = fromEntity.propContactDetails!=null ? fromEntity.propContactDetails :this.propContactDetails;
		this.propServiceProvider = fromEntity.propServiceProvider!=null ? fromEntity.propServiceProvider :this.propServiceProvider;
		this.propServiceProviderCategory = fromEntity.propServiceProviderCategory!=null ? fromEntity.propServiceProviderCategory :this.propServiceProviderCategory;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Date getWarrantyStart() {
		return warrantyStart;
	}

	public void setWarrantyStart(Date warrantyStart) {
		this.warrantyStart = warrantyStart;
	}

	public Date getWarrantyEnd() {
		return warrantyEnd;
	}

	public void setWarrantyEnd(Date warrantyEnd) {
		this.warrantyEnd = warrantyEnd;
	}

	public String getPersonIncharge() {
		return personIncharge;
	}

	public void setPersonIncharge(String personIncharge) {
		this.personIncharge = personIncharge;
	}

	public PropAssetsCategory getPropAssetsCategory() {
		return propAssetsCategory;
	}

	public void setPropAssetsCategory(PropAssetsCategory propAssetsCategory) {
		this.propAssetsCategory = propAssetsCategory;
	}

	public PropServiceProvider getPropServiceProvider() {
		return propServiceProvider;
	}

	public void setPropServiceProvider(PropServiceProvider propServiceProvider) {
		this.propServiceProvider = propServiceProvider;
	}

	public PropContactDetails getPropContactDetails() {
		return propContactDetails;
	}

	public void setPropContactDetails(PropContactDetails propContactDetails) {
		this.propContactDetails = propContactDetails;
	}

	public PropAnnualReminders getPropAnnualReminders() {
		return propAnnualReminders;
	}

	public void setPropAnnualReminders(PropAnnualReminders propAnnualReminders) {
		this.propAnnualReminders = propAnnualReminders;
	}

	public PropServiceProviderCategory getPropServiceProviderCategory() {
		return propServiceProviderCategory;
	}

	public void setPropServiceProviderCategory(PropServiceProviderCategory propServiceProviderCategory) {
		this.propServiceProviderCategory = propServiceProviderCategory;
	}

	public String getServiceProviderCategoryId() {
		return serviceProviderCategoryId;
	}

	public void setServiceProviderCategoryId(String serviceProviderCategoryId) {
		this.serviceProviderCategoryId = serviceProviderCategoryId;
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

	public String getAssetsCategoryId() {
		return assetsCategoryId;
	}

	public void setAssetsCategoryId(String assetsCategoryId) {
		this.assetsCategoryId = assetsCategoryId;
	}

	public String getServiceProviderId() {
		return serviceProviderId;
	}

	public void setServiceProviderId(String serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}

}
