package com.bms.eai.common.security.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.UrlPathHelper;

import com.bms.eai.common.security.entity.UserIdentity;
import com.bms.eai.common.security.principal.AuthenticationWithToken;
import com.bms.eai.common.security.principal.UserIdentityUserDetails;
import com.bms.eai.common.security.service.TokenService;
import com.bms.eai.common.security.service.UserSessionService;
import com.bms.eai.portal.config.support.RequestParamTokenEndpointsProvider;
import com.bms.eai.portal.security.lib.SecurityApiConstants;
import com.google.common.base.Optional;

/**
 * @author kul_sudhakar
 *
 */
@SuppressWarnings("rawtypes")
@Service
public class DefaultUserSessionServiceImpl implements UserSessionService {

	@Autowired
	private TokenService tokenService;

	@Autowired(required = false)
    private List<RequestParamTokenEndpointsProvider> requestParamTokenEndpointsProviders;
	
	@Override
    public Optional<Authentication> safeGetAuthentication(HttpServletRequest request) {
        Optional<String> token = safeGetToken(request);
        if (token.isPresent()) {
            return Optional.fromNullable(tokenService.retrieve(token.get()));
        } else {
            return Optional.absent();
        }
    }
	
	@Override
    public Optional<Authentication> safeGetCurrentAuthentication() {
        return Optional.fromNullable(SecurityContextHolder.getContext().getAuthentication());
    }

    @Override
    public String getToken(HttpServletRequest request) throws AccessDeniedException {
        Optional<String> token = safeGetToken(request);
        if (token.isPresent()) {
            return token.get();
        } else {
            throw accessDenied();
        }
    }

    @Override
    public String getToken(Authentication auth) throws AccessDeniedException {
        Optional<String> token = safeGetToken(auth);
        if (token.isPresent()) {
            return token.get();
        } else {
            throw accessDenied();
        }
    }

    @Override
    public Optional<String> safeGetToken(HttpServletRequest request) {
        String resourcePath = new UrlPathHelper().getPathWithinApplication(request);
        Optional<String> token = Optional.fromNullable(request.getHeader(SecurityApiConstants.TOKEN_HEADER_KEY));
        if (!token.isPresent() && !CollectionUtils.isEmpty(requestParamTokenEndpointsProviders)) {
            for (RequestParamTokenEndpointsProvider endpointsProvider : requestParamTokenEndpointsProviders) {
                if (endpointsProvider.isEndpointStartedWith(resourcePath, request.getMethod())) {
                    token = Optional.fromNullable(request.getParameter(SecurityApiConstants.TOKEN_HEADER_KEY));
                    break;
                }
            }
        }
        return token;
    }

    @Override
    public Optional<String> safeGetToken(Authentication auth) {
        if (auth instanceof AuthenticationWithToken) {
            return Optional.fromNullable(((AuthenticationWithToken) auth).getToken());
        } else {
            return Optional.absent();
        }
    }

    @Override
    public UserIdentityUserDetails getUserDetails(HttpServletRequest request) throws AccessDeniedException {
        Optional<UserIdentityUserDetails> userDetails = safeGetUserDetails(request);
        if (userDetails.isPresent()) {
            return userDetails.get();
        } else {
            throw accessDenied();
        }
    }

	@Override
    public UserIdentityUserDetails getUserDetails(Authentication auth) throws AccessDeniedException {
        Optional<UserIdentityUserDetails> userDetails = safeGetUserDetails(auth);
        if (userDetails.isPresent()) {
            return userDetails.get();
        } else {
            throw accessDenied();
        }
    }

    @Override
    public UserIdentityUserDetails getCurrentUserDetails() throws AccessDeniedException {
        Optional<UserIdentityUserDetails> userDetails = safeGetCurrentUserDetails();
        if (userDetails.isPresent()) {
            return userDetails.get();
        } else {
            throw accessDenied();
        }
    }

    @Override
    public Optional<UserIdentityUserDetails> safeGetUserDetails(HttpServletRequest request) {
        Optional<Authentication> auth = safeGetAuthentication(request);
        if (auth.isPresent()) {
            return safeGetUserDetails(auth.get());
        } else {
            return Optional.absent();
        }
    }

    @Override
    public Optional<UserIdentityUserDetails> safeGetUserDetails(Authentication auth) {
        Object principal = auth.getPrincipal();
        if (principal != null && principal instanceof UserIdentityUserDetails) {
            return Optional.fromNullable((UserIdentityUserDetails) auth.getPrincipal());
        } else {
            return Optional.absent();
        }
    }

    @Override
    public Optional<UserIdentityUserDetails> safeGetCurrentUserDetails() {
        Optional<Authentication> auth = safeGetCurrentAuthentication();
        if (auth.isPresent()) {
            return safeGetUserDetails(auth.get());
        }
        return Optional.absent();
    }

    @Override
    public UserIdentity getUserIdentity(HttpServletRequest request) throws AccessDeniedException {
        Optional<UserIdentity> userIdentity = safeGetUserIdentity(request);
        if (userIdentity.isPresent()) {
            return userIdentity.get();
        } else {
            throw accessDenied();
        }
    }

    @Override
    public UserIdentity getUserIdentity(Authentication auth) throws AccessDeniedException {
        Optional<UserIdentity> userIdentity = safeGetUserIdentity(auth);
        if (userIdentity.isPresent()) {
            return userIdentity.get();
        } else {
            throw accessDenied();
        }
    }

    @Override
    public UserIdentity getCurrentUserIdentity() throws AccessDeniedException {
        Optional<UserIdentity> userIdentity = safeGetCurrentUserIdentity();
        if (userIdentity.isPresent()) {
            return userIdentity.get();
        } else {
            throw accessDenied();
        }
    }

    @Override
    public Optional<UserIdentity> safeGetUserIdentity(HttpServletRequest request) {
        Optional<Authentication> auth = safeGetAuthentication(request);
        if (auth.isPresent()) {
            return safeGetUserIdentity(auth.get());
        } else {
            return Optional.absent();
        }
    }

    @Override
    public Optional<UserIdentity> safeGetUserIdentity(Authentication auth) {
        Optional<UserIdentityUserDetails> userDetails = safeGetUserDetails(auth);
        if (userDetails.isPresent()) {
            return Optional.fromNullable(userDetails.get().getUserIdentity());
        } else {
            return Optional.absent();
        }
    }

    @Override
    public Optional<UserIdentity> safeGetCurrentUserIdentity() {
        Optional<Authentication> auth = safeGetCurrentAuthentication();
        if (auth.isPresent()) {
            return safeGetUserIdentity(auth.get());
        } else {
            return Optional.absent();
        }
    }

    @Override
    public void removeUser(HttpServletRequest httpRequest) {
        Optional<String> token = safeGetToken(httpRequest);
        if (token.isPresent()) {
            tokenService.remove(token.get());
        }
    }

    @Override
    public void removeUser(Authentication auth) {
        Optional<String> token = safeGetToken(auth);
        if (token.isPresent()) {
            tokenService.remove(token.get());
        }
    }

    @Override
    public void removeCurrentUser() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                String token = getToken(auth);
                if (token != null) {
                    tokenService.remove(token);
                }
            }
        }
    }

    private AccessDeniedException accessDenied() {
        return new AccessDeniedException("Access denied due to invalid user session.");
    }
	
}
