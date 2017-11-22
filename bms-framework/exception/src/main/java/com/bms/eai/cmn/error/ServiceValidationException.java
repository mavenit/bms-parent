package com.bms.eai.cmn.error;

import org.springframework.stereotype.Component;

/**
 * @author kul_sudhakar
 *
 */
@Component
public class ServiceValidationException extends ServiceException  {

	private static final long serialVersionUID = -3707760206938616409L;

	public ServiceValidationException(){}
	
	public ServiceValidationException(String errorCode, Object... args) {
        super(errorCode, args);
    }
	
	
}
