package com.bms.eai.portal.constants;

import com.bms.eai.portal.lib.CmnUtil;

/**
 * @author kul_sudhakar
 *
 */
public class PageConstants {

public static final String ROOT 				= "/";
	
	public static final String LOGIN 	= "/login";
	public static final String HOME 	= "/home";
	
	public static final String FRGT_PWORD 	= "/forgetpass";
	public static final String USER_REGISTER 	= "/register";
	
	public static final String LOGIN_SUC 			= "/login-success";
	public static final String LOGIN_ERR 			= "/login-error";
	public static final String LOGOUT 				= "/logout-process";
	
	
	public static final String ERROR 	= "/error";
	
	
	public static String getRedirectUrl(String url) {
		return getRedirectUrl(url, null);
	}
	
	public static String getRedirectUrl(String url, String pathVar) {
		String newURL = "redirect:" + url;
		if(!CmnUtil.isObjNull(pathVar)) {
			newURL = newURL + "/" + pathVar;
		}
		return newURL;
	}
	
	
}
