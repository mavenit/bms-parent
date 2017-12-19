package com.bms.eai.portal.config.idm;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bms.eai.portal.lib.CmnUtil;
import com.bms.eai.portal.model.UserProfile;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author kul_sudhakar
 *
 */
@JsonInclude(Include.NON_NULL)
public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 3999424365047511704L;
	
	UserProfile profile;
	// TokenDto authToken;
	Boolean enabled = true;
	Boolean accountNonExpired = true;
	Boolean credentialsNonExpired = true;
	Boolean accountNonLocked = true;
	Boolean forceLogout = false;
	String errorCode;
	private List<GrantedAuthority> authorities;

	public CustomUserDetails(UserProfile profile) {
		this.profile = profile;
	}

	/*
	 * public CustomUserDetails(UserProfile profile, TokenDto authToken) {
	 * this.profile = profile; this.authToken = authToken; }
	 */

	/*
	 * public CustomUserDetails(UserProfile profile, TokenDto authToken) {
	 * this.profile = profile; this.authToken = authToken; }
	 */

	public CustomUserDetails(UserProfile profile,
			// TokenDto authToken,
			Boolean enabled, Boolean accountNonExpired, Boolean credentialsNonExpired, Boolean accountNonLocked,
			List<GrantedAuthority> authorities) {
		this.profile = profile;
		// this.authToken = authToken;
		this.enabled = enabled;
		this.accountNonExpired = accountNonExpired;
		this.credentialsNonExpired = credentialsNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return !CmnUtil.isObjNull(profile) ? profile.getPassword() : null;
	}

	@Override
	public String getUsername() {
		return !CmnUtil.isObjNull(profile) ? profile.getUsername() : null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public UserProfile getProfile() {
		return profile;
	}

	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}

	/*
	 * public void setAuthToken(TokenDto authToken) { this.authToken =
	 * authToken; }
	 * 
	 * public TokenDto getAuthToken() { return authToken; }
	 */

	public void setForceLogout(Boolean forceLogout) {
		this.forceLogout = forceLogout;
	}

	public Boolean getForceLogout() {
		return forceLogout;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
