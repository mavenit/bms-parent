package com.bms.eai.property.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.StringUtils;

import com.bms.eai.common.model.core.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

 
@SuppressWarnings("serial")
@JsonRootName("facilities")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "prop_facilities", uniqueConstraints = @UniqueConstraint(columnNames = "pf_type"))
public class PropFacilities extends AbstractEntity<PropFacilities,String>  implements java.io.Serializable {

	@JsonIgnore
	@Id
	@Column(name = "pf_id", length = 45)
	private String id;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pdm_id", nullable = false)
	private PropDetailsMaster propDetailsMaster;
	
	@JsonProperty("type")
	@NotEmpty(message="empty.type")
	@Size(min = 1, max = 200, message = "error.type.size")
	@Column(name = "pf_type", unique = true, nullable = false, length = 250)
	private String type;
	
	@JsonProperty("propDetailsMasterId")
	@NotNull(message="empty.propDetailsMasterId")
	@Transient
	private String propDetailsMasterId;
	
	public PropFacilities() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPropDetailsMasterId() {
		return propDetailsMasterId;
	}

	public void setPropDetailsMasterId(String propDetailsMasterId) {
		this.propDetailsMasterId = propDetailsMasterId;
	}

	public PropDetailsMaster getPropDetailsMaster() {
		return this.propDetailsMaster;
	}

	public void setPropDetailsMaster(PropDetailsMaster propDetailsMaster) {
		this.propDetailsMaster = propDetailsMaster;
	}

	@Override
	protected void doCopyUpdateFieldsFrom(PropFacilities fromEntity) {
		this.propDetailsMaster = fromEntity.propDetailsMaster!=null ? fromEntity.propDetailsMaster : this.propDetailsMaster;
		this.type = StringUtils.hasText(fromEntity.type) ? fromEntity.type : this.type ;
	}

}
