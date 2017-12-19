package com.bms.eai.property.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Filter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.StringUtils;

import com.bms.eai.common.model.core.AbstractEntity;
import com.bms.eai.common.model.entity.PropCountry;
import com.bms.eai.common.model.entity.PropState;
import com.bms.eai.constants.RegExpression;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

 
@SuppressWarnings("serial")
@JsonRootName("contactDetails")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "prop_contact_details")
public class PropContactDetails extends AbstractEntity<PropContactDetails,String>  implements java.io.Serializable {

	@JsonIgnore
	@Id
	@Column(name = "pcd_id", length = 45)
	private String id;
	
	@JsonProperty("country")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pc_id")
	private PropCountry propCountry;
	
	@JsonProperty("state")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ps_id")
	private PropState propState;
	
	@JsonProperty("addressLine1")
	@NotEmpty(message="empty.addressLine1")
	@Pattern(regexp=RegExpression.REGEXP_STRRING_SPACE,message="error.str.regex")
	@Size(min = 1, max = 200, message = "error.addressLine1.size")
	@Column(name = "pcd_address_line1", nullable = false, length = 250)
	private String addressLine1;
	
	@JsonProperty("addressLine2")
	@Column(name = "pcd_address_line2", length = 150)
	private String addressLine2;
	
	@JsonProperty("poscode")
	@NotEmpty(message="empty.poscode")
	@Pattern(regexp=RegExpression.REGEXP_STRRING_SPACE,message="error.str.regex")
	@Size(min = 5, max = 5, message = "error.poscode.size")
	@Column(name = "pcd_poscode", nullable = false, length = 15)
	private String poscode;
	
	@JsonProperty("city")
	@NotEmpty(message="empty.city")
	@Pattern(regexp=RegExpression.REGEXP_STRRING_SPACE,message="error.str.regex")
	@Size(min = 1, max = 200, message = "error.city.size")
	@Column(name = "pcd_city", length = 250)
	private String city;
	
	@JsonProperty("phno")
	@NotEmpty(message="empty.phno")
	@Pattern(regexp=RegExpression.REGEXP_STRRING_SPACE,message="error.str.regex")
	@Size(min = 1, max = 200, message = "error.phno.size")
	@Column(name = "pcd_office_phno", length = 15)
	private String phno;
	
	@JsonProperty("faxno")
	@NotEmpty(message="empty.faxno")
	@Pattern(regexp=RegExpression.REGEXP_STRRING_SPACE,message="error.str.regex")
	@Size(min = 1, max = 200, message = "error.faxno.size")
	@Column(name = "pcd_office_faxno", length = 20)
	private String faxno;
	
	@JsonProperty("mobileno")
	@NotEmpty(message="empty.mobileno")
	@Pattern(regexp=RegExpression.REGEXP_STRRING_SPACE,message="error.str.regex")
	@Size(min = 10, max = 10, message = "error.mobileno.size")
	@Column(name = "pcd_mobileno", nullable = false, length = 15)
	private String mobileno;
	
	@JsonProperty("email")
	@NotEmpty(message="empty.email")
	@Email(message="invalid.email")
	@Pattern(regexp=RegExpression.REGEXP_STRRING_SPACE,message="error.str.regex")
	@Size(min = 1, max = 200, message = "error.email.size")
	@Column(name = "pcd_emailid", nullable = false, length = 250)
	private String email;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "propContactDetails")
	@Filter(name = AbstractEntity.NON_DELETED_FILTER_NAME)
	private List<PropServiceProvider> propServiceProviders ;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "propContactDetails")
	@Filter(name = AbstractEntity.NON_DELETED_FILTER_NAME)
	private List<PropAssets> propAssetses ;

	@JsonProperty("countryId")
	@NotNull(message="empty.countryId")
	@Transient
	private String countryId;
	
	@JsonProperty("stateId")
	@NotNull(message="empty.stateId")
	@Transient
	private String stateId;
	
	public PropContactDetails() {}

	@Override
	protected void doCopyUpdateFieldsFrom(PropContactDetails fromEntity) {
		this.addressLine1 = StringUtils.hasText(fromEntity.addressLine1) ? fromEntity.addressLine1 : this.addressLine1;
		this.addressLine2 = StringUtils.hasText(fromEntity.addressLine2) ? fromEntity.addressLine2 : this.addressLine2;
		this.city = StringUtils.hasText(fromEntity.city) ? fromEntity.city : this.city;
		this.email = StringUtils.hasText(fromEntity.email) ? fromEntity.email : this.email;
		this.faxno = StringUtils.hasText(fromEntity.faxno) ? fromEntity.faxno : this.faxno;
		this.mobileno = StringUtils.hasText(fromEntity.mobileno) ? fromEntity.mobileno : this.mobileno;
		this.phno = StringUtils.hasText(fromEntity.phno) ? fromEntity.phno : this.phno;
		this.poscode = StringUtils.hasText(fromEntity.poscode) ? fromEntity.poscode : this.poscode;
		//this.propAssetses = (fromEntity.propAssetses!=null && !CollectionUtils.isEmpty(fromEntity.propAssetses))?fromEntity.propAssetses:this.propAssetses ;
		//this.propServiceProviders =  (fromEntity.propServiceProviders!=null && !CollectionUtils.isEmpty(fromEntity.propServiceProviders))?fromEntity.propServiceProviders:this.propServiceProviders;
		this.propState = fromEntity.propState!=null ? fromEntity.propState :this.propState;
		this.propCountry = fromEntity.propCountry!=null ? fromEntity.propCountry :this.propCountry;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PropCountry getPropCountry() {
		return propCountry;
	}

	public void setPropCountry(PropCountry propCountry) {
		this.propCountry = propCountry;
	}

	public PropState getPropState() {
		return propState;
	}

	public void setPropState(PropState propState) {
		this.propState = propState;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getPoscode() {
		return poscode;
	}

	public void setPoscode(String poscode) {
		this.poscode = poscode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	public String getFaxno() {
		return faxno;
	}

	public void setFaxno(String faxno) {
		this.faxno = faxno;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<PropServiceProvider> getPropServiceProviders() {
		return propServiceProviders;
	}

	public void setPropServiceProviders(List<PropServiceProvider> propServiceProviders) {
		this.propServiceProviders = propServiceProviders;
	}

	public List<PropAssets> getPropAssetses() {
		return propAssetses;
	}

	public void setPropAssetses(List<PropAssets> propAssetses) {
		this.propAssetses = propAssetses;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

}
