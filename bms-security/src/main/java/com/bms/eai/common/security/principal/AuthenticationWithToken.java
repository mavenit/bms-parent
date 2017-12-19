package com.bms.eai.common.security.principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

/**
 * @author kul_sudhakar
 *
 */
public class AuthenticationWithToken extends PreAuthenticatedAuthenticationToken {

	public AuthenticationWithToken(Authentication delegate) {
        super(delegate.getPrincipal(), delegate.getPrincipal(), delegate.getAuthorities());
    }

    public void setToken(String token) {
        super.setDetails(token);
    }

    public String getToken() {
        return (String) super.getDetails();
    }

	
}
