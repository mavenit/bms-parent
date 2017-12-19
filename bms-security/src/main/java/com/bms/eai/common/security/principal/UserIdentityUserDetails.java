package com.bms.eai.common.security.principal;

import org.springframework.security.core.userdetails.UserDetails;

import com.bms.eai.common.security.entity.UserIdentity;

/**
 * @author kul_sudhakar
 *
 */
public interface UserIdentityUserDetails <T extends UserIdentity> extends UserDetails{

	 T getUserIdentity();
	
}
