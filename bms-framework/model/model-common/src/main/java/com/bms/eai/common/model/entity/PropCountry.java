package com.bms.eai.common.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.bms.eai.common.model.core.AbstractEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "prop_country", uniqueConstraints = @UniqueConstraint(columnNames = "pc_name"))
@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS)
public class PropCountry extends AbstractEntity<PropCountry,String>  implements java.io.Serializable {

	@Id
	@Column(name = "pc_id", length = 45)
	private String id;

	@Column(name = "pc_name", unique = true, length = 150)
	private String pcName;

	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "propCountry")
	private List<PropContactDetails> propContactDetailses ;*/

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "propCountry")
	@Filter(name = AbstractEntity.NON_DELETED_FILTER_NAME)
	@NotFound(action=NotFoundAction.IGNORE)
	private List<PropState> propStates ;

	public PropCountry() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getPcName() {
		return this.pcName;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName;
	}

	/*public List<PropContactDetails> getPropContactDetailses() {
		return this.propContactDetailses;
	}

	public void setPropContactDetailses(List<PropContactDetails> propContactDetailses) {
		this.propContactDetailses = propContactDetailses;
	}*/

	public List<PropState> getPropStates() {
		return this.propStates;
	}

	public void setPropStates(List<PropState> propStates) {
		this.propStates = propStates;
	}

	@Override
	protected void doCopyUpdateFieldsFrom(PropCountry fromEntity) {
		
	}


}
