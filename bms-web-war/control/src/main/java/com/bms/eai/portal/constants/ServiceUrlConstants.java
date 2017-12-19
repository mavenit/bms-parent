package com.bms.eai.portal.constants;

/**
 * @author kul_sudhakar
 *
 */
public class ServiceUrlConstants {

private static final String BE_HOSTIP = "http://localhost:8080";
	
	//public static final String INSERT = "INSERT";
	
	private static final String BE_CTXPATH = "/bms-be";
	
	public static final String URL_LOGIN 		 	= BE_HOSTIP+BE_CTXPATH+"/profile/login";
	public static final String USER_PROFILE_LIST 	= BE_HOSTIP+BE_CTXPATH+"/profile/list";
	public static final String USER_PROFILE_CREATE 	= BE_HOSTIP+BE_CTXPATH+"/profile/create";
	
	public static final String URL_CHAGE_PASSWORD	= BE_HOSTIP+BE_CTXPATH+"/pwdchange";
	
	public static final String BYLOGINID_URL 	= BE_HOSTIP+BE_CTXPATH+"/signin/";
	
	public static final String SIGNUP_URL 	= BE_HOSTIP+BE_CTXPATH+"/signup";
	
}
