package com.bms.eai.property.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
@JsonRootName("parkingBayDetails")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "prop_parking_bay_details")
public class PropParkingBayDetails extends AbstractEntity<PropParkingBayDetails,String>  implements java.io.Serializable {

	@JsonIgnore
	@Id
	@Column(name = "ppbd_id", length = 45)
	private String id;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pbd_id", nullable = false)
	private PropBlockDetails propBlockDetails;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pdm_id", nullable = false)
	private PropDetailsMaster propDetailsMaster;
	
	@JsonProperty("floor")
	@NotNull(message="empty.floor")
	@Min(value=1,message="error.min.value")
	@Max(value=25,message="error.max.value")
	@Column(name = "ppbd_floor", nullable = false)
	private Integer floor;
	
	@JsonProperty("noBays")
	@NotNull(message="empty.noBays")
	@Min(value=1,message="error.min.value")
	@Max(value=25,message="error.max.value")
	@Column(name = "ppbd_no_bays", nullable = false)
	private Integer noBays;
	
	@JsonProperty("desc")
	@NotEmpty(message="empty.desc")
	@Size(min = 1, max = 40, message = "error.desc.size")
	@Column(name = "ppbd_desc", length = 150)
	private String desc;

	@JsonProperty("propDetailsMasterId")
	@NotNull(message="empty.propDetailsMasterId")
	@Transient
	private String propDetailsMasterId;
	
	@JsonProperty("propBlockDetailsId")
	@NotNull(message="empty.propBlockDetailsId")
	@Transient
	private String propBlockDetailsId;
	
	public PropParkingBayDetails() {}
	 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public PropBlockDetails getPropBlockDetails() {
		return this.propBlockDetails;
	}

	public void setPropBlockDetails(PropBlockDetails propBlockDetails) {
		this.propBlockDetails = propBlockDetails;
	}

	public PropDetailsMaster getPropDetailsMaster() {
		return this.propDetailsMaster;
	}

	public void setPropDetailsMaster(PropDetailsMaster propDetailsMaster) {
		this.propDetailsMaster = propDetailsMaster;
	}
	
	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public Integer getNoBays() {
		return noBays;
	}

	public void setNoBays(Integer noBays) {
		this.noBays = noBays;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPropDetailsMasterId() {
		return propDetailsMasterId;
	}

	public void setPropDetailsMasterId(String propDetailsMasterId) {
		this.propDetailsMasterId = propDetailsMasterId;
	}

	public String getPropBlockDetailsId() {
		return propBlockDetailsId;
	}

	public void setPropBlockDetailsId(String propBlockDetailsId) {
		this.propBlockDetailsId = propBlockDetailsId;
	}

	@Override
	protected void doCopyUpdateFieldsFrom(PropParkingBayDetails fromEntity) {
		this.desc = StringUtils.hasText(fromEntity.desc) ? fromEntity.desc : this.desc;
		this.floor = fromEntity.floor!=null ? fromEntity.floor : this.floor;
		this.noBays = fromEntity.noBays!=null ? fromEntity.noBays : this.noBays;
		this.propBlockDetails = fromEntity.propBlockDetails!=null ? fromEntity.propBlockDetails : this.propBlockDetails ;
		this.propDetailsMaster = fromEntity.propDetailsMaster!=null ? fromEntity.propDetailsMaster : this.propDetailsMaster;
	}

}
