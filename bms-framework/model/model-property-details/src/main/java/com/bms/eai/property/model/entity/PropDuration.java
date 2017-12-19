package com.bms.eai.property.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Filter;
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
@JsonRootName("duration")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "prop_duration", uniqueConstraints = @UniqueConstraint(columnNames = "pd_type"))
public class PropDuration extends AbstractEntity<PropDuration,String>  implements java.io.Serializable {
	
	@JsonIgnore
	@Id
	@Column(name = "pd_id", length = 45)
	private String id;
	
	@JsonProperty("type")
	@NotEmpty(message="empty.type")
	@Pattern(regexp=RegExpression.REGEXP_STRRING_SPACE,message="error.str.regex")
	@Size(min = 1, max = 200, message = "error.type.size")
	@Column(name = "pd_type", unique = true, length = 250)
	private String type;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "propDuration")
	@Filter(name = AbstractEntity.NON_DELETED_FILTER_NAME)
	private List<PropServiceProvider> propServiceProviders ;

	public PropDuration() {}

	@Override
	protected void doCopyUpdateFieldsFrom(PropDuration fromEntity) {
		this.type = StringUtils.hasText(fromEntity.type) ? fromEntity.type : this.type;
		//this.propServiceProviders = (fromEntity.propServiceProviders!=null && !CollectionUtils.isEmpty(fromEntity.propServiceProviders))?fromEntity.propServiceProviders:this.propServiceProviders ;
	}

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

	public List<PropServiceProvider> getPropServiceProviders() {
		return propServiceProviders;
	}

	public void setPropServiceProviders(List<PropServiceProvider> propServiceProviders) {
		this.propServiceProviders = propServiceProviders;
	}

}
