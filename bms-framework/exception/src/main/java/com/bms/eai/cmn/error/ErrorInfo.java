package com.bms.eai.cmn.error;

/**
 * @author kul_sudhakar
 *
 */
public class ErrorInfo {

	private String errorCode = null;
    private String errorMessage = null;
    private String fullErrorCode = null;
    private Object[] arguments = null;

    public ErrorInfo(ExceptionBase ex) {
        this(ex.getErrorCode(), ex.getMessage(), ex.getFullErrorCode(), ex.getArgs());
    }

    public ErrorInfo(String errorCode, String errorMessage, String fullErrorCode, Object... arguments) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.fullErrorCode = fullErrorCode;
        this.arguments = arguments;
    }
    
    public String getErrorCode() {
        return errorCode;
    }

    public String getFullErrorCode() {
        return fullErrorCode;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
	
	
}
