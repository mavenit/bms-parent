package com.bms.eai.portal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author kul_sudhakar
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginBean {

	private String userName;
	private String password;
	private String newPassword;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
