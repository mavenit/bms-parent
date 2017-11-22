package com.bms.eai.cmn.error.support;

import org.springframework.stereotype.Component;

import com.bms.eai.cmn.error.ServiceException;
import com.bms.eai.cmn.error.ServiceJsonProcessingException;
import com.bms.eai.cmn.error.ServiceValidationException;

/**
 * @author kul_sudhakar
 *
 */
@Component
public class ServiceExceptionHelper {

	public ServiceExceptionHelper() {}
	
	public static ServiceException wrap(Exception e, String errorCode, Object... arguments) {
        if (e instanceof ServiceValidationException) {
            return (ServiceException) e;
        } else {
            return new ServiceException(e, errorCode, arguments);
        }
    }

    public static ServiceValidationException invalid(String errorCode, Object... arguments) {
        return new ServiceValidationException(errorCode, arguments);
    }

    public static ServiceJsonProcessingException invalidJsonProcessing(String errorCode, Object... arguments) {
        return new ServiceJsonProcessingException(invalid(errorCode, arguments));
    }
	
}
