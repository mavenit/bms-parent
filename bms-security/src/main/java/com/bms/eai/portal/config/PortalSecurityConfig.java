package com.bms.eai.portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bms.eai.common.security.authentication.impl.TokenAuthenticationProviderImpl;
import com.bms.eai.common.security.authentication.impl.UsernamePasswordAuthenticationProviderImpl;

/**
 * @author kul_sudhakar
 *
 */
@Configuration
public class PortalSecurityConfig {

	@Bean
	public TokenAuthenticationProviderImpl tokenAuthenticationProvider() {
		return new TokenAuthenticationProviderImpl();
	}

	@Bean
	public UsernamePasswordAuthenticationProviderImpl usernamePasswordAuthenticationProvider() {
		return new UsernamePasswordAuthenticationProviderImpl();
	}

}
