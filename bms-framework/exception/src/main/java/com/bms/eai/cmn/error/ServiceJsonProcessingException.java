package com.bms.eai.cmn.error;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author kul_sudhakar
 *
 */
public class ServiceJsonProcessingException extends JsonProcessingException {

	private static final long serialVersionUID = 6742543477820377326L;

	public ServiceJsonProcessingException(ServiceValidationException rootCause) {
        super(rootCause);
    }

    public ServiceValidationException getCause() {
        return (ServiceValidationException) super.getCause();
    }
	
	
}
