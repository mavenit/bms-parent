package com.bms.eai.cmn.error.support;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.NoSuchMessageException;

/**
 * @author kul_sudhakar
 *
 */
public class ErrorMessageSourceHelper implements MessageSourceAware  {

	private static final Locale DEFAULT_LOCALE = Locale.ENGLISH;
	
	private MessageSource messageSource;
	
    public String getMessage(String errorCode, Object... args) {
        try {
        	return messageSource.getMessage(errorCode, args, resolveLocale());
        } catch (NoSuchMessageException e) {
            return null;
        }
    }
    
    public String getMessage(String errorCode) {
        try {
        	return messageSource.getMessage(errorCode,null, resolveLocale());
        } catch (NoSuchMessageException e) {
            return null;
        }
    }
    
    private Locale resolveLocale() {
    	 return DEFAULT_LOCALE;
    }

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

}
