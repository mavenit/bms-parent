package com.bms.eai.portal.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.bms.eai.portal.config.idm.CustomUserDetails;
import com.bms.eai.portal.constants.CmnConstants;
import com.bms.eai.portal.dto.UserProfileDto;
import com.bms.eai.portal.model.UserProfile;

/**
 * @author kul_sudhakar
 *
 */
public abstract class AbstractController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired 
	protected ServletContext context;
	
	@Autowired 
	protected UserProfileDto userProfileDto;
	
	@InitBinder
	protected void bindingPreparation(WebDataBinder binder) {
		DateFormat format = new SimpleDateFormat(CmnConstants.DT_DD_MM_YYYY_Slash);
		
		CustomDateEditor editor = new CustomDateEditor(format, true);
		binder.registerCustomEditor(Date.class, editor);
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
		binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, false));
		//binder.registerCustomEditor(CustomMultipartFile.class, new MultiPartPropertyEditor());
	}
	
	public boolean isUserAuthenticated() {		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//is user logged in
		if (!(auth instanceof AnonymousAuthenticationToken)) {
		    return true;
		}
		return false;
	}
	
	public String getRealPath() {
		return context.getRealPath("/");
	}
	
	public UserProfile getCurrentUser() {
		UserProfile userProfile = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth.isAuthenticated()) {
			CustomUserDetails cud = (CustomUserDetails) auth.getPrincipal();
			userProfile = cud.getProfile();
		}
		return userProfile;
	}
	
	public String getCurrentUserId() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth.isAuthenticated()) {
			return auth.getName();
		}
		return "";
	}
	
	
}
