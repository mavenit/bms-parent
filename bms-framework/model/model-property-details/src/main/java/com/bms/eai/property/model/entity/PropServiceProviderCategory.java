package com.bms.eai.property.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Filter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.StringUtils;

import com.bms.eai.common.model.core.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@SuppressWarnings("serial")
@JsonRootName("serviceProviderCategory")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "prop_service_provider_category",uniqueConstraints = @UniqueConstraint(columnNames = "pspc_name"))
public class PropServiceProviderCategory extends AbstractEntity<PropServiceProviderCategory,String>  implements java.io.Serializable {

	@JsonIgnore
	@Id
	@Column(name = "pspc_id", length = 45)
	private String id;
	
	@JsonProperty("name")
	@NotEmpty(message="empty.name")
	@Size(min = 1, max = 40, message = "error.name.size")
	@Column(name = "pspc_name", unique = true, nullable = false, length = 250)
	private String name;
	
	@JsonProperty("desc")
	@Column(name = "pspc_desc", length = 65535)
	private String desc;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "propServiceProviderCategory")
	@Filter(name = AbstractEntity.NON_DELETED_FILTER_NAME)
	private List<PropAssets> propAssetses;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "propServiceProviderCategory")
	@Filter(name = AbstractEntity.NON_DELETED_FILTER_NAME)
	private List<PropServiceProvider> propServiceProviders ;

	public PropServiceProviderCategory() {}
	 
	@Override
	protected void doCopyUpdateFieldsFrom(PropServiceProviderCategory fromEntity) {
		this.name = StringUtils.hasText(fromEntity.name) ? fromEntity.name : this.name;
		this.desc = StringUtils.hasText(fromEntity.desc) ? fromEntity.desc : this.desc;
		//this.propAssetses = (fromEntity.propAssetses!=null && !CollectionUtils.isEmpty(fromEntity.propAssetses))?fromEntity.propAssetses:this.propAssetses ;
		//this.propServiceProviders =  (fromEntity.propServiceProviders!=null && !CollectionUtils.isEmpty(fromEntity.propServiceProviders))?fromEntity.propServiceProviders:this.propServiceProviders;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<PropAssets> getPropAssetses() {
		return propAssetses;
	}

	public void setPropAssetses(List<PropAssets> propAssetses) {
		this.propAssetses = propAssetses;
	}

	public List<PropServiceProvider> getPropServiceProviders() {
		return propServiceProviders;
	}

	public void setPropServiceProviders(List<PropServiceProvider> propServiceProviders) {
		this.propServiceProviders = propServiceProviders;
	}

}
