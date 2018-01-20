package com.bms.eai.portal.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ModelAttribute;

import com.bms.eai.common.lib.ResourcePathConstants;
import com.bms.eai.module.common.beans.CommonBean;
import com.bms.eai.portal.config.idm.CustomUserDetails;
import com.bms.eai.portal.constants.CmnConstants;
import com.bms.eai.portal.model.UserProfile;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author kul_sudhakar
 *
 */
public abstract class AbstractController /*extends AbstractRestTemplate*/ {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	private Integer defaultPageSize = 10;
	
	@Autowired
	protected CommonDataService commonDataService;

	@Autowired 
	protected ServletContext context;
	
	@Autowired
	protected ObjectMapper objectMapper;
	
	@InitBinder
	protected void bindingPreparation(WebDataBinder binder) {
		DateFormat format = new SimpleDateFormat(CmnConstants.DT_DD_MM_YYYY_Slash);
		
		CustomDateEditor editor = new CustomDateEditor(format, true);
		binder.registerCustomEditor(Date.class, editor);
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
		binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, false));
		//binder.registerCustomEditor(CustomMultipartFile.class, new MultiPartPropertyEditor());
	}
	
	
	protected String generateMAVMsg(String appendMsg,String id) {
		StringBuffer msg = new StringBuffer();
		msg.append(appendMsg); //CmnConstants.MAV_SUCCESS_UPDATE_MSG);
		msg.append(CmnConstants.SPACE);
		msg.append(CmnConstants.MAV_SUCCESS_MSG_ID);
		msg.append(CmnConstants.LEFT_PARANTHESES);
		msg.append(id); //t.getId());
		msg.append(CmnConstants.RIGHTT_PARANTHESES);
		return msg.toString();
	}
	
	@ModelAttribute("countryList")
	public List<CommonBean> getCountryList() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(ResourcePathConstants.SERVICE_NAME, ResourcePathConstants.COUNTRY_RESOURCE_PATH);
		map.put(ResourcePathConstants.OPERATION_NAME, ResourcePathConstants.LOADALL);
		return commonDataService.processStaticData(map);
	}
	
	@ModelAttribute("propTypeList")
	public List<CommonBean> getPropTypeList() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(ResourcePathConstants.SERVICE_NAME, ResourcePathConstants.PROPTYPE_RESOURCE_PATH);
		map.put(ResourcePathConstants.OPERATION_NAME, ResourcePathConstants.LOADALL);
		return commonDataService.processStaticData(map);
	}
	
	@ModelAttribute("stateList")
	public List<CommonBean> getStateList() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(ResourcePathConstants.SERVICE_NAME, ResourcePathConstants.STATE_RESOURCE_PATH);
		map.put(ResourcePathConstants.OPERATION_NAME, ResourcePathConstants.LOADALL);
		return commonDataService.processStaticData(map);
	}
	
	//@ModelAttribute("statesByCountry")
	public List<CommonBean> getStateListByCountry(String countryId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(ResourcePathConstants.SERVICE_NAME, ResourcePathConstants.STATE_RESOURCE_PATH);
		map.put(ResourcePathConstants.OPERATION_NAME, ResourcePathConstants.LOADBYCOUNTRY);
		map.put(ResourcePathConstants.ID, countryId);
		return commonDataService.processStaticData(map);
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

	public Integer getDefaultPageSize() {
		return defaultPageSize;
	}

	public void setDefaultPageSize(Integer defaultPageSize) {
		this.defaultPageSize = defaultPageSize;
	}
	
}
