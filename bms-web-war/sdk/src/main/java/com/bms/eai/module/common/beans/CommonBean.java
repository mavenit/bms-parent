package com.bms.eai.module.common.beans;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author kul_sudhakar
 *
 */
@JsonRootName("EventDetails")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class CommonBean {

	@JsonProperty("name")
	private String tagName;
	
	@JsonProperty("desc")
	private String tagDesc;
	
	public CommonBean() {}
	
	public CommonBean (String tagName) {this.tagName=tagName;}
	
	public CommonBean (String tagName,String tagDesc) {
		this.tagName=tagName;
		this.tagDesc=tagDesc;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTagDesc() {
		return tagDesc;
	}

	public void setTagDesc(String tagDesc) {
		this.tagDesc = tagDesc;
	}

}
