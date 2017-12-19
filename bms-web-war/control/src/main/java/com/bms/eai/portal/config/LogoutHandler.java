package com.bms.eai.portal.config;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.bms.eai.portal.config.idm.CustomUserDetails;
import com.bms.eai.portal.lib.DateUtil;

/**
 * @author kul_sudhakar
 *
 */
@Component
public class LogoutHandler extends SimpleUrlLogoutSuccessHandler {

protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	protected ServletContext context;
	
	@SuppressWarnings("unused")
	@Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
		logger.info("FORCE_LOGOUT>>>>" + request.getAttribute("FORCE_LOGOUT"));
		boolean forceLogout = false;
		String errorCode = null;
        if (authentication != null) {
        	//String authToken = null;
        	if(authentication.getPrincipal() instanceof CustomUserDetails) {
				CustomUserDetails cud = (CustomUserDetails) authentication.getPrincipal();				
				forceLogout = cud.getForceLogout();
				errorCode = cud.getErrorCode();
			}
        	logger.info(authentication.getName() + " logged out at " + DateUtil.getCurrentDate());
        	if(!forceLogout) {
        		try {
        			//getIdmService(authToken).logout();
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
        	}
        }   
        logger.info("Redirect: " + getDefaultTargetUrl());
        super.onLogoutSuccess(request, response, authentication);   
    }
	
}
