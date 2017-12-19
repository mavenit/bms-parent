package com.bms.eai.module.prop.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author kul_sudhakar
 *
 */
@JsonRootName("PropBlockDetails")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropBlockDetails {

	@JsonProperty("propDetailsMasterId")
	private String propDetailsMasterId;

	@JsonProperty("pdbNoFloors")
	private int pdbNoFloors;

	@JsonProperty("pdbBlockName")
	private String pdbBlockName;

	@JsonProperty("pbdNoUnits")
	private int pbdNoUnits;

	public String getPropDetailsMasterId() {
		return propDetailsMasterId;
	}

	public void setPropDetailsMasterId(String propDetailsMasterId) {
		this.propDetailsMasterId = propDetailsMasterId;
	}

	public int getPdbNoFloors() {
		return pdbNoFloors;
	}

	public void setPdbNoFloors(int pdbNoFloors) {
		this.pdbNoFloors = pdbNoFloors;
	}

	public String getPdbBlockName() {
		return pdbBlockName;
	}

	public void setPdbBlockName(String pdbBlockName) {
		this.pdbBlockName = pdbBlockName;
	}

	public int getPbdNoUnits() {
		return pbdNoUnits;
	}

	public void setPbdNoUnits(int pbdNoUnits) {
		this.pbdNoUnits = pbdNoUnits;
	}

}
