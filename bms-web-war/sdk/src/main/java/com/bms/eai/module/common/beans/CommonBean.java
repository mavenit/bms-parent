package com.bms.eai.module.common.beans;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.bms.eai.module.core.AbstractSdkEntity;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author kul_sudhakar
 *
 */
//@JsonRootName("commonBean")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class CommonBean extends AbstractSdkEntity{

	@JsonProperty("id")
	private String id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("desc")
	private String desc;
	
	public CommonBean() {}
	
	public CommonBean (String name) {this.name=name;}
	
	public CommonBean (String name,String desc) {
		this.name=name;
		this.desc=desc;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
