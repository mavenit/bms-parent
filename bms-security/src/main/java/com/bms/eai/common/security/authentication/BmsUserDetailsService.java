package com.bms.eai.common.security.authentication;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.bms.eai.cmn.error.ServiceException;

/**
 * @author kul_sudhakar
 *
 */
public interface BmsUserDetailsService extends UserDetailsService {

	long countUsersWithPasswordPolicy(Integer passwordPolicyId) throws ServiceException;
	
}
