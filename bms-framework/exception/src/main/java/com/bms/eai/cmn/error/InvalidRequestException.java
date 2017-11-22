package com.bms.eai.cmn.error;

/**
 * @author kul_sudhakar
 *
 */
public class InvalidRequestException extends ExceptionBase  {

	private static final long serialVersionUID = 8909746317652098640L;

	public InvalidRequestException(String errorCode, Object... args) {
        super(errorCode, args);
    }

    public InvalidRequestException(Throwable cause, String errorCode, Object... args) {
        super(cause, errorCode, args);
    }
	
}
