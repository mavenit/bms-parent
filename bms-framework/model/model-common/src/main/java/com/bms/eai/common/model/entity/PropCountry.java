package com.bms.eai.common.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.StringUtils;

import com.bms.eai.common.model.core.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@SuppressWarnings("serial")
@JsonRootName("country")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "prop_country", uniqueConstraints = @UniqueConstraint(columnNames = "pc_name"))
//@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS)
public class PropCountry extends AbstractEntity<PropCountry,String>  implements java.io.Serializable {

	@JsonProperty("id")
	@Id
	@Column(name = "pc_id", length = 45)
	private String id;

	@JsonProperty("name")
	@NotEmpty(message="empty.country")
	@Column(name = "pc_name", unique = true, length = 150)
	private String name;

	@JsonProperty("state")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
	@Filter(name = AbstractEntity.NON_DELETED_FILTER_NAME)
	@NotFound(action=NotFoundAction.IGNORE)
	// @JsonBackReference
	private List<PropState> propStates ;

	public PropCountry() {
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

	public List<PropState> getPropStates() {
		return this.propStates;
	}

	public void setPropStates(List<PropState> propStates) {
		this.propStates = propStates;
	}

	@Override
	protected void doCopyUpdateFieldsFrom(PropCountry fromEntity) {
		this.name=StringUtils.hasText(fromEntity.name)?fromEntity.name:this.name;
	}


}
