package com.bms.eai.cmn.error;

import org.springframework.stereotype.Component;

import com.bms.eai.error.codes.FrameworkErrorCodes;

/**
 * @author kul_sudhakar
 *
 */
@Component
public class ServiceException extends ExceptionBase {

	private static final long serialVersionUID = 1248145659251769793L;

	public ServiceException() {}
	
	public ServiceException(Throwable cause) {
        super(cause, FrameworkErrorCodes.UNHANDLED_ERROR, cause.getMessage());
    }

    public ServiceException(String errorCode, Object... args) {
        super(errorCode, args);
    }

    public ServiceException(Throwable cause, String errorCode, Object... args) {
        super(cause, errorCode, args);
    }
	
}
