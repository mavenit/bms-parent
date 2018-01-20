package com.bms.eai.common.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@JsonRootName("state")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "prop_state", uniqueConstraints = @UniqueConstraint(columnNames = "ps_name"))
//@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS )
public class PropState extends AbstractEntity<PropState,String> implements java.io.Serializable {

	@JsonProperty("id")
	@Id
	@Column(name = "ps_id", length = 45)
	private String id;
	
	@JsonProperty("country")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	@NotFound(action=NotFoundAction.IGNORE)
	private PropCountry country;
	
	@JsonProperty("name")
	@NotEmpty(message="empty.state")
	@Column(name = "ps_name", unique = true, length = 250)
	private String name;
	
	public PropState() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PropCountry getCountry() {
		return country;
	}

	public void setCountry(PropCountry country) {
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	protected void doCopyUpdateFieldsFrom(PropState fromEntity) {
		this.name=StringUtils.hasText(fromEntity.name)?fromEntity.name:this.name;
	}

}
