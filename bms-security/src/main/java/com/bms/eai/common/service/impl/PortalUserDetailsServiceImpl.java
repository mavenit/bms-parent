package com.bms.eai.common.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bms.eai.cmn.error.ServiceException;
import com.bms.eai.common.security.authentication.BmsUserDetailsService;
import com.bms.eai.common.security.entity.PortalUserIdentity;
import com.bms.eai.common.security.entity.PortalUserIdentityUserDetails;
import com.bms.eai.common.service.AccountService;
import com.bms.eai.portal.security.entity.Account;

/**
 * @author kul_sudhakar
 *
 */
@Service
public class PortalUserDetailsServiceImpl implements BmsUserDetailsService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${bms.portal.account.password.policy.id}")
	private Integer passwordPolicyId;

	@Autowired
	private AccountService accountService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account;
		try {
			account = accountService.getByUsername(username);
		} catch (ServiceException e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
		if (account != null) {
			if (account.isActive()) {
				PortalUserIdentity identity = new PortalUserIdentity(account);
				return new PortalUserIdentityUserDetails(identity);
			} else {
				logger.info("Account for {} is inactive.", username);
				throw new DisabledException("Account inactive");
			}
		} else {
			throw new UsernameNotFoundException("Invalid login.");
		}
	}

	@Override
	public long countUsersWithPasswordPolicy(Integer passwordPolicyId) throws ServiceException {
		return passwordPolicyId == passwordPolicyId ? 1 : 0;
	}

}
