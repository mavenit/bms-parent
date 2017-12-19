package com.bms.eai.module.prop.beans;

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
public class PropDetailsMaster {

	@JsonProperty("propState")
	private String propState;

	@JsonProperty("propType")
	private String propType;

	@JsonProperty("pdmName")
	private String pdmName;

	@JsonProperty("pdmTotalUnits")
	private int pdmTotalUnits;

	@JsonProperty("pdmEmail")
	private String pdmEmail;

	@JsonProperty("pdmJmbMc")
	private String pdmJmbMc;

	@JsonProperty("pdmLogo")
	private byte[] pdmLogo;

	@JsonProperty("pdmDescription")
	private String pdmDescription;

	public String getPropState() {
		return propState;
	}

	public void setPropState(String propState) {
		this.propState = propState;
	}

	public String getPropType() {
		return propType;
	}

	public void setPropType(String propType) {
		this.propType = propType;
	}

	public String getPdmName() {
		return pdmName;
	}

	public void setPdmName(String pdmName) {
		this.pdmName = pdmName;
	}

	public int getPdmTotalUnits() {
		return pdmTotalUnits;
	}

	public void setPdmTotalUnits(int pdmTotalUnits) {
		this.pdmTotalUnits = pdmTotalUnits;
	}

	public String getPdmEmail() {
		return pdmEmail;
	}

	public void setPdmEmail(String pdmEmail) {
		this.pdmEmail = pdmEmail;
	}

	public String getPdmJmbMc() {
		return pdmJmbMc;
	}

	public void setPdmJmbMc(String pdmJmbMc) {
		this.pdmJmbMc = pdmJmbMc;
	}

	public byte[] getPdmLogo() {
		return pdmLogo;
	}

	public void setPdmLogo(byte[] pdmLogo) {
		this.pdmLogo = pdmLogo;
	}

	public String getPdmDescription() {
		return pdmDescription;
	}

	public void setPdmDescription(String pdmDescription) {
		this.pdmDescription = pdmDescription;
	}

}
