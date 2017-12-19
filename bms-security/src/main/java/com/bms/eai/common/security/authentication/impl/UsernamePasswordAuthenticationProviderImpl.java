package com.bms.eai.common.security.authentication.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;

import com.bms.eai.common.security.authentication.AuthenticationProviderDelegate;
import com.bms.eai.common.security.authentication.BmsAuthenticationProvider;
import com.bms.eai.common.security.principal.AuthenticationWithToken;
import com.bms.eai.common.security.service.TokenService;
import com.google.common.base.Optional;

/**
 * @author kul_sudhakar
 *
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class UsernamePasswordAuthenticationProviderImpl implements BmsAuthenticationProvider {

	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
	
    @Autowired
    private TokenService tokenService = null;

    @Autowired
    private AuthenticationProviderDelegate delegate = null;
    
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		Optional<String> username = (Optional) auth.getPrincipal();
		Optional<String> password = (Optional) auth.getCredentials();

        if (!username.isPresent() || !password.isPresent()) {
            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }

        AuthenticationWithToken resultAuth = authentication(username.get(), password.get());
        String newToken = tokenService.generateNewToken();
        resultAuth.setToken(newToken);
        tokenService.store(newToken, resultAuth);

        return resultAuth;
	}

	private AuthenticationWithToken authentication(String username, String password) {
        Authentication delegateAuth = new UsernamePasswordAuthenticationToken(username, password);
        Authentication delegateResultAuth = delegate.authenticate(delegateAuth);
        return new AuthenticationWithToken(delegateResultAuth);
    }
	
	@Override
	public boolean supports(Class<?> authentication) {
		 return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
}
