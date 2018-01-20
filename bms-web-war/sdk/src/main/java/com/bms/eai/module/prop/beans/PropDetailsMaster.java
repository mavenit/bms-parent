package com.bms.eai.module.prop.beans;

import java.io.File;

import com.bms.eai.module.beans.FileUploadDetails;
import com.bms.eai.module.core.AbstractSdkEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author kul_sudhakar
 *
 */
@JsonRootName("PropDetailsMaster")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropDetailsMaster extends AbstractSdkEntity /*extends FileUploadDetails*/ {

	@JsonProperty("id")
	private String id;
	
	@JsonProperty("state") 
	private PropState propState;
	
	@JsonProperty("propType")
	private PropType propType;
	
	@JsonProperty("stateId")
	private String stateId;

	@JsonProperty("propTypeId")
	private String propTypeId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("totalUnits")
	private Integer totalUnits;

	@JsonProperty("email")
	private String email;

	@JsonProperty("jmbMc")
	private String jmbMc;

	/*@JsonProperty("pdmLogo")
	private byte[] pdmLogo;*/
	
	private File file;

	@JsonProperty("description")
	private String description;
	
	@JsonIgnore
	private FileUploadDetails fileUploadDetails;

	public PropDetailsMaster () {}
	
	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getPropTypeId() {
		return propTypeId;
	}

	public void setPropTypeId(String propTypeId) {
		this.propTypeId = propTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTotalUnits() {
		return totalUnits;
	}

	public void setTotalUnits(Integer totalUnits) {
		this.totalUnits = totalUnits;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJmbMc() {
		return jmbMc;
	}

	public void setJmbMc(String jmbMc) {
		this.jmbMc = jmbMc;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PropState getPropState() {
		return propState;
	}

	public void setPropState(PropState propState) {
		this.propState = propState;
	}

	public PropType getPropType() {
		return propType;
	}

	public void setPropType(PropType propType) {
		this.propType = propType;
	}

	public FileUploadDetails getFileUploadDetails() {
		return fileUploadDetails;
	}

	public void setFileUploadDetails(FileUploadDetails fileUploadDetails) {
		this.fileUploadDetails = fileUploadDetails;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
