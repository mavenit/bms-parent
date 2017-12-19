package com.bms.eai.property.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.StringUtils;

import com.bms.eai.common.model.core.AbstractEntity;
import com.bms.eai.constants.RegExpression;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;

@SuppressWarnings("serial")
@JsonRootName("assetsCategory")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "prop_assets_category", uniqueConstraints = @UniqueConstraint(columnNames = "pac_type"))
public class PropAssetsCategory extends AbstractEntity<PropAssetsCategory,String>  implements java.io.Serializable {

	@JsonIgnore
	@Id
	@Column(name = "pac_id", length = 45)
	private String id;
	
	@NotEmpty(message="empty.type")
	@Pattern(regexp=RegExpression.REGEXP_STRRING_SPACE,message="error.str.regex")
	@Size(min = 1, max = 200, message = "error.type.size")
	@Column(name = "pac_type", unique = true, nullable = false, length = 250)
	private String type;
	
	public PropAssetsCategory() {}

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

	@Override
	protected void doCopyUpdateFieldsFrom(PropAssetsCategory fromEntity) {
		this.type = StringUtils.hasText(fromEntity.type) ? fromEntity.type : this.type;
	}
}
