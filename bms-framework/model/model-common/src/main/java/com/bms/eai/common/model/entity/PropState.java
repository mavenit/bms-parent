package com.bms.eai.common.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.bms.eai.common.model.core.AbstractEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "prop_state", uniqueConstraints = @UniqueConstraint(columnNames = "ps_name"))
@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS )
public class PropState extends AbstractEntity<PropState,String> implements java.io.Serializable {

	@Id
	@Column(name = "ps_id", length = 45)
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pc_id")
	@NotFound(action=NotFoundAction.IGNORE)
	private PropCountry propCountry;
	
	@Column(name = "ps_name", unique = true, length = 250)
	private String psName;
	
	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "propState")
	private List<PropDetailsMaster> propDetailsMasters ;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "propState")
	private List<PropContactDetails> propContactDetailses;*/

	public PropState() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PropCountry getPropCountry() {
		return this.propCountry;
	}

	public void setPropCountry(PropCountry propCountry) {
		this.propCountry = propCountry;
	}

	public String getPsName() {
		return this.psName;
	}

	public void setPsName(String psName) {
		this.psName = psName;
	}

	/*public List<PropDetailsMaster> getPropDetailsMasters() {
		return this.propDetailsMasters;
	}

	public void setPropDetailsMasters(List<PropDetailsMaster> propDetailsMasters) {
		this.propDetailsMasters = propDetailsMasters;
	}

	public List<PropContactDetails> getPropContactDetailses() {
		return this.propContactDetailses;
	}

	public void setPropContactDetailses(List<PropContactDetails> propContactDetailses) {
		this.propContactDetailses = propContactDetailses;
	}*/

	@Override
	protected void doCopyUpdateFieldsFrom(PropState fromEntity) {
		
	}

}
