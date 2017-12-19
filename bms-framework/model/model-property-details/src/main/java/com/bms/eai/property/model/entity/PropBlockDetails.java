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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
@JsonRootName("blockDetails")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "prop_block_details", uniqueConstraints = @UniqueConstraint(columnNames = "pdb_block_name"))
public class PropBlockDetails extends AbstractEntity<PropBlockDetails,String>  implements java.io.Serializable {

	@JsonIgnore
	@Id
	@Column(name = "pbd_id",  length = 45)
	private String id;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pdm_id", nullable = false)
	private PropDetailsMaster propDetailsMaster;
	
	@JsonProperty("noFloors")
	@NotNull(message="empty.noFloors")
	@Min(value=1,message="error.min.value")
	@Max(value=25,message="error.max.value")
	@Column(name = "pdb_no_floors", nullable = false)
	private Integer noFloors;
	
	@JsonProperty("blockName")
	@NotEmpty(message="empty.blockname")
	@Pattern(regexp=RegExpression.REGEXP_STRRING_SPACE,message="error.str.regex")
	@Size(min = 1, max = 40, message = "error.blockname.size")
	@Column(name = "pdb_block_name", unique = true, nullable = false, length = 45)
	private String blockName;
	
	@JsonProperty("noUnits")
	@NotNull(message="empty.noUnits")
	@Min(value=1,message="error.min.value")
	@Max(value=25,message="error.max.value")
	@Column(name = "pbd_no_units", nullable = false)
	private Integer noUnits;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "propBlockDetails")
	private List<PropParkingBayDetails> propParkingBayDetailses;
	
	@JsonProperty("propDetailsMasterId")
	@NotNull(message="empty.propDetailsMasterId")
	@Transient
	private String propDetailsMasterId;

	public PropBlockDetails() {}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PropDetailsMaster getPropDetailsMaster() {
		return propDetailsMaster;
	}

	public void setPropDetailsMaster(PropDetailsMaster propDetailsMaster) {
		this.propDetailsMaster = propDetailsMaster;
	}

	public Integer getNoFloors() {
		return noFloors;
	}

	public void setNoFloors(Integer noFloors) {
		this.noFloors = noFloors;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public Integer getNoUnits() {
		return noUnits;
	}

	public void setNoUnits(Integer noUnits) {
		this.noUnits = noUnits;
	}

	public List<PropParkingBayDetails> getPropParkingBayDetailses() {
		return propParkingBayDetailses;
	}

	public void setPropParkingBayDetailses(List<PropParkingBayDetails> propParkingBayDetailses) {
		this.propParkingBayDetailses = propParkingBayDetailses;
	}

	public String getPropDetailsMasterId() {
		return propDetailsMasterId;
	}

	public void setPropDetailsMasterId(String propDetailsMasterId) {
		this.propDetailsMasterId = propDetailsMasterId;
	}

	@Override
	protected void doCopyUpdateFieldsFrom(PropBlockDetails fromEntity) {
		this.noUnits = fromEntity.noUnits!=null?fromEntity.noUnits:this.noUnits;
		this.blockName = StringUtils.hasText(fromEntity.blockName)?fromEntity.blockName:this.blockName;
		this.noFloors = fromEntity.noFloors!=null?fromEntity.noFloors:this.noFloors;
		this.propDetailsMaster = fromEntity.propDetailsMaster!=null?fromEntity.propDetailsMaster:this.propDetailsMaster;
	}

}
