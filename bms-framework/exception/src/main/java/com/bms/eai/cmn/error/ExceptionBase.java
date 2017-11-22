package com.bms.eai.cmn.error;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import com.bms.eai.cmn.error.support.ErrorMessageSourceHelper;

/**
 * @author kul_sudhakar
 *
 */
public class ExceptionBase extends Exception {

	private static final long serialVersionUID = -2661802419958184631L;

	private static final String ERROR_CODE_DELIMITER = "-";
    protected  String errorCode="";
    protected  Object[] args={};
    
    private ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"config-exp-msg-source.xml"});
    
    private final ErrorMessageSourceHelper errorMessageSourceHelper = (ErrorMessageSourceHelper)context.getBean("errorMessageSourceHelper");;
    
   public ExceptionBase() {}
    
    public ExceptionBase(String errorCode, Object... args) {
        this(null, errorCode, args);
    }
    
    public ExceptionBase(Throwable cause, String errorCode, Object... args) {
        super("Error code: " + errorCode, cause);
        this.errorCode = errorCode;
        this.args = args;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Object[] getArgs() {
        return args;
    }

    public String getFullErrorCode() {
        StringBuilder sb = new StringBuilder(getErrorCode());
        Throwable cause = super.getCause();
        while (cause instanceof ExceptionBase) {
            sb.append(ERROR_CODE_DELIMITER);
            ExceptionBase causeEx = (ExceptionBase) cause;
            sb.append(causeEx.getErrorCode());
            cause = cause.getCause();
        }
        return sb.toString();
    }

    @Override
    public String getMessage() {
        String message;
        if (getErrorCode() != null) {
        	if(errorMessageSourceHelper!=null) {
            	message = errorMessageSourceHelper.getMessage(getErrorCode(), getArgs());
                if (message == null) {
                    message = createNotFoundMessage();
                }
            } else {
                message = "MessageSource not initialized! Error message: " + super.getMessage();
            }
        } else {
            message = "No error code defined. Error message: " + super.getMessage();
        }
        return message;
    }

    protected String createNotFoundMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("Message not found for error code: ")
                .append(getErrorCode()).append(".");
        if (getArgs() != null && getArgs().length > 0) {
            sb.append(" Arguments: [")
                    .append(StringUtils.arrayToDelimitedString(getArgs(), ","))
                    .append("].");
        }
        return sb.toString();
    }
	
	
}
