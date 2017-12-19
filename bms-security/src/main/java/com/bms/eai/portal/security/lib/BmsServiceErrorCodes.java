package com.bms.eai.portal.security.lib;

/**
 * @author kul_sudhakar
 *
 */
public class BmsServiceErrorCodes {

	/**
     * Message:
     * Error searching account by account details with search term {0}. Cause: {1}.
     */
    public static final String SEARCH_ACCOUNT_BY_ACCOUNT_DETAILS_ERROR = "VC232";
	
    /**
     * Message:
     * Error searching account by owner details with search term {0}. Cause: {1}.
     */
    public static final String SEARCH_ACCOUNT_BY_OWNER_DETAILS_ERROR = "VC233";
    
    /**
     * Message:
     * Error getting registrant account for username: {0}. Cause: {1}.
     */
    public static final String GET_BY_USERNAME_ERROR = "VC241";
    
    /**
     * Message:
     * Failed to update account to active. Account {0} not found.
     */
    public static final String UPDATE_TO_ACTIVE_ACCOUNT_NOT_FOUND = "VC218";
    
    /**
     * Message:
     * Error updating account {0} to active. Cause: {1}.
     */
    public static final String UPDATE_TO_ACTIVE_ERROR = "VC244";
    
    /**
     * Message:
     * Error encrypting password [{0}] for username {1}. Cause: {2}.
     */
    public static final String ADD_ACCOUNT_ENCRYPTION_ERROR = "VC243";
    
    /**
     * Message:
     * Error checking if username {0} exists. Cause: {1}.
     */
    public static final String CHECK_USERNAME_EXISTS_ERROR = "VC242";
    
}
