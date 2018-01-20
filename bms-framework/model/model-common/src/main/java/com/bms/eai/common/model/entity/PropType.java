package com.bms.eai.common.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.StringUtils;

import com.bms.eai.common.model.core.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@SuppressWarnings("serial")
@JsonRootName("propType")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "prop_type", uniqueConstraints = @UniqueConstraint(columnNames = "pt_type"))
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PropType extends AbstractEntity<PropType, String> implements java.io.Serializable {

	@JsonProperty("id")
	@Id
	@Column(name = "pt_id", length = 50)
	private String id;

	@JsonProperty("name")
	@Column(name = "pt_type", unique = true, nullable = false, length = 150)
	@NotEmpty(message="empty.proptype")
	private String name;

	@JsonProperty("desc")
	@Column(name = "pt_desc", length = 65535)
	private String desc;

	public PropType() {}

	@Override
	protected void doCopyUpdateFieldsFrom(PropType fromEntity) {
		this.name=StringUtils.hasText(fromEntity.name)?fromEntity.name:this.name;
		this.desc=StringUtils.hasText(fromEntity.desc)?fromEntity.desc:this.desc;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
