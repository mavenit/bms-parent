package com.bms.eai.common.security.authentication.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import com.bms.eai.common.security.authentication.BmsAuthenticationProvider;
import com.bms.eai.common.security.service.TokenService;
import com.google.common.base.Optional;

/**
 * @author kul_sudhakar
 *
 */
public class TokenAuthenticationProviderImpl implements BmsAuthenticationProvider {

	@Autowired
	private TokenService tokenService = null;
	 
	@SuppressWarnings({"unchecked","rawtypes"})
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Optional<String> token = (Optional) authentication.getPrincipal();
	        if (!token.isPresent() || token.get().isEmpty()) {
	            throw new BadCredentialsException("Invalid token");
	        }
	        if (!tokenService.contains(token.get())) {
	            throw new BadCredentialsException("Invalid token or token expired");
	        }
	        return tokenService.retrieve(token.get());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		 return authentication.equals(PreAuthenticatedAuthenticationToken.class);
	}

}
