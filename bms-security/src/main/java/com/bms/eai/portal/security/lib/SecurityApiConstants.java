package com.bms.eai.portal.security.lib;

/**
 * @author kul_sudhakar
 *
 */
public class SecurityApiConstants {

	public static final String MODULE_NAME = "security";
    public static final String API_BASE_URL = CommonApiConstants.API_BASE_URL + "/" + MODULE_NAME;
    public static final String AUTHENTICATE_URL = API_BASE_URL + "/authenticate";
    public static final String LOGOUT_URL = API_BASE_URL + "/logout";

    public static final String USERNAME_HEADER_KEY = "X-Auth-Username";
    public static final String PASSWORD_HEADER_KEY = "X-Auth-Password";
    public static final String TOKEN_HEADER_KEY = "X-Auth-Token";
	
}
