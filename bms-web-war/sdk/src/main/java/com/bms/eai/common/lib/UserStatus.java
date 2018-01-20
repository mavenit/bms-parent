package com.bms.eai.common.lib;

/**
 * @author kul_sudhakar
 *
 */
public enum UserStatus {
	SUCESS("00"),
    ACTIVATED("00"),
    SAVED("00"),
    UPDATED("00"),
    DELETED("00"),
    INACTIVE("99"),
    FAIL("99"),
	PENDING("99"),
    ;
	
	private String statusCode;
	
	UserStatus(String statusCode) {
        this.statusCode = statusCode;
    }

    public String statusCode() {
        return statusCode;
    }
}
