package com.bms.eai.common.security.service;

import org.springframework.security.core.Authentication;

/**
 * @author kul_sudhakar
 *
 */
public interface TokenService {

	String generateNewToken();

    void store(String token, Authentication authentication);

    boolean contains(String token);

    Authentication retrieve(String token);

    void remove(String token);

    void removeByUsername(String username);
	
}
