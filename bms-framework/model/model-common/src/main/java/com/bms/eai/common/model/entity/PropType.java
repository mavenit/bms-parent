package com.bms.eai.common.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.bms.eai.common.model.core.AbstractEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "prop_type", uniqueConstraints = @UniqueConstraint(columnNames = "pt_type"))
@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS )
public class PropType extends AbstractEntity<PropType,String>  implements java.io.Serializable {

	@Id
	@Column(name = "pt_id", length = 50)
	private String id;
	
	@Column(name = "pt_type", unique = true, nullable = false, length = 150)
	private String ptType;
	
	@Column(name = "pt_desc", length = 65535)
	private String ptDesc;
	
	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "propType")
	private List<PropDetailsMaster> propDetailsMasters;*/

	public PropType() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPtType() {
		return this.ptType;
	}

	public void setPtType(String ptType) {
		this.ptType = ptType;
	}

	public String getPtDesc() {
		return this.ptDesc;
	}

	public void setPtDesc(String ptDesc) {
		this.ptDesc = ptDesc;
	}

	/*public List<PropDetailsMaster> getPropDetailsMasters() {
		return this.propDetailsMasters;
	}

	public void setPropDetailsMasters(List<PropDetailsMaster> propDetailsMasters) {
		this.propDetailsMasters = propDetailsMasters;
	}*/
	
	@Override
	protected void doCopyUpdateFieldsFrom(PropType fromEntity) {
		
	}

}
