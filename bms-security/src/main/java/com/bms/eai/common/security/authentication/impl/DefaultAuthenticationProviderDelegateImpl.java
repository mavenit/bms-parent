package com.bms.eai.common.security.authentication.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.bms.eai.common.security.authentication.AuthenticationProviderDelegate;
import com.bms.eai.common.security.authentication.BmsUserDetailsService;
import com.bms.eai.common.security.encryption.EncryptionManager;
import com.bms.eai.common.security.encryption.EncryptionToken;
import com.bms.eai.common.security.entity.UserIdentity;
import com.bms.eai.common.security.principal.UserIdentityUserDetails;
import com.bms.eai.error.codes.FrameworkErrorCodes;

/**
 * @author kul_sudhakar
 *
 */
@SuppressWarnings("rawtypes")
public class DefaultAuthenticationProviderDelegateImpl extends DaoAuthenticationProvider
		implements AuthenticationProviderDelegate {

	@Autowired
	private EncryptionManager encryptionManager;

	@Autowired
	private BmsUserDetailsService userDetailsService;

	@PostConstruct
	public void init() {
		super.setUserDetailsService(userDetailsService);
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		UserIdentityUserDetails identityUserDetails = (UserIdentityUserDetails) userDetails;
		UserIdentity identity = identityUserDetails.getUserIdentity();
		EncryptionToken encrypted = new EncryptionToken(identity.getPassword(), identity.getSalt(),
				identity.getIteration());
		if (!encryptionManager.equals(authentication.getCredentials().toString(), encrypted)) {
			logger.debug("Authentication failed: password does not match stored value");
			throw new BadCredentialsException(
					messages.getMessage(FrameworkErrorCodes.BAD_CREDENTIALS, "Bad credentials"));
		}
	}

}
