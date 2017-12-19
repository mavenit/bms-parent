package com.bms.eai.common.security.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;

import com.bms.eai.common.security.entity.UserIdentity;
import com.bms.eai.common.security.principal.UserIdentityUserDetails;
import com.google.common.base.Optional;

/**
 * @author kul_sudhakar
 *
 */
@SuppressWarnings("rawtypes")
public interface UserSessionService {

	Optional<Authentication> safeGetAuthentication(HttpServletRequest request);
    Optional<Authentication> safeGetCurrentAuthentication();

    String getToken(HttpServletRequest request) throws AccessDeniedException;
    String getToken(Authentication auth) throws AccessDeniedException;

    Optional<String> safeGetToken(HttpServletRequest request);
    Optional<String> safeGetToken(Authentication auth);
    
	UserIdentityUserDetails getUserDetails(HttpServletRequest request) throws AccessDeniedException;
    UserIdentityUserDetails getUserDetails(Authentication auth) throws AccessDeniedException;
    UserIdentityUserDetails getCurrentUserDetails() throws AccessDeniedException;

    Optional<UserIdentityUserDetails> safeGetUserDetails(HttpServletRequest request);
    Optional<UserIdentityUserDetails> safeGetUserDetails(Authentication auth);
    Optional<UserIdentityUserDetails> safeGetCurrentUserDetails();

    UserIdentity getUserIdentity(HttpServletRequest request) throws AccessDeniedException;
    UserIdentity getUserIdentity(Authentication auth) throws AccessDeniedException;
    UserIdentity getCurrentUserIdentity() throws AccessDeniedException;

    Optional<UserIdentity> safeGetUserIdentity(HttpServletRequest request);
    Optional<UserIdentity> safeGetUserIdentity(Authentication auth);
    Optional<UserIdentity> safeGetCurrentUserIdentity();

    void removeUser(HttpServletRequest request);
    void removeUser(Authentication auth);
    void removeCurrentUser();
	
}
