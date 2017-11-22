package com.bms.eai.property.be.boot.config;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bms.eai.cmn.error.ErrorInfo;
import com.bms.eai.cmn.error.InvalidRequestException;
import com.bms.eai.cmn.error.ServiceException;
import com.bms.eai.cmn.error.ServiceValidationException;
import com.bms.eai.cmn.error.support.ErrorMessageSourceHelper;
 

/**
 * @author kul_sudhakar
 *
 */
@ControllerAdvice
public class PropertyGlobalExceptionHandler {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"config-exp-msg-source.xml"});
	    
	private final ErrorMessageSourceHelper errorMessageSourceHelper = (ErrorMessageSourceHelper)context.getBean("errorMessageSourceHelper");;
	   
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(HttpMessageNotWritableException.class)
	@ResponseBody
	public ErrorInfo handleSerializationError(HttpMessageNotWritableException e) throws Exception {
		log.error(e.getMessage(), e);
		return new ErrorInfo("error.write.response", e.getMessage(), "error.write.response");
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
   @ExceptionHandler(HttpMessageNotReadableException.class)
   @ResponseBody
   public ErrorInfo handleDeserializationError(HttpMessageNotReadableException oriEx)
           throws Exception {
       Exception e = tryToFindServiceException(oriEx);
       log.error(e.getMessage(), e);
       if (e instanceof ServiceException) {
           return new ErrorInfo((ServiceException) e);
       } else {
           return new ErrorInfo("error.read.request", e.getMessage(), "error.read.request");
       }
   }
	
	@ResponseStatus(HttpStatus.FORBIDDEN)
   @ExceptionHandler(AccessDeniedException.class)
   @ResponseBody
   public ErrorInfo handleAccessDeniedError(AccessDeniedException e)
           throws Exception {
       log.error(e.getMessage(), e);
       return new ErrorInfo("error.access.denied", e.getMessage(), "error.access.denied");
   }
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
   @ExceptionHandler(MethodArgumentNotValidException.class)
   @ResponseBody
   public ErrorInfo handleValidationError(MethodArgumentNotValidException e)
           throws Exception {
       BindingResult result = e.getBindingResult();
       List<ObjectError> errors = result.getAllErrors();
       Object[] errorCodes = new String[errors.size()];
       int i = 0;
       for (ObjectError error : errors) {
           String[] codes = error.getCodes();
           errorCodes[i++] = codes[codes.length - 1];
       }
       return new ErrorInfo("error.validation.failed", e.getMessage(), "error.validation.failed", errorCodes);
   }
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
   @ExceptionHandler(IllegalArgumentException.class)
   @ResponseBody
   public ErrorInfo handleIllegalArgumentException(IllegalArgumentException e) throws IOException {
       return handleStandardException(e, "error.illegal.argument");
   }
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UnsupportedOperationException.class)
	@ResponseBody
	public ErrorInfo handleUnsupportedOperationException(UnsupportedOperationException e) throws IOException {
	   return handleStandardException(e, "error.unsupported.operation");
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
   @ExceptionHandler(InvalidRequestException.class)
   @ResponseBody
   public ErrorInfo handleInvalidRequestException(InvalidRequestException e) throws IOException {
       log.error(e.getMessage(), e);
       return new ErrorInfo(e);
   }

   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   @ExceptionHandler(ServiceException.class)
   @ResponseBody
   public ErrorInfo handleServiceException(ServiceException e) throws IOException {
       log.error(e.getMessage(), e);
       return new ErrorInfo(e);
   }
	
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   @ExceptionHandler(ServiceValidationException.class)
   @ResponseBody
   public ErrorInfo handleServiceValidationException(ServiceValidationException e) throws IOException {
       log.error(e.getMessage());
       return new ErrorInfo(e);
   }

   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   @ExceptionHandler(Exception.class)
   @ResponseBody
   public ErrorInfo handleOtherError(Exception e) throws IOException {
       return handleStandardException(e, "error.unknown");
   }

   private ErrorInfo handleStandardException(Exception e, String defaultErrorCode) {
       String message = e.getMessage();
       log.error(message, e);
       // Test if message is an error code with message defined.
       String errorMessage = errorMessageSourceHelper.getMessage(message);
       if (errorMessage != null) {
           return new ErrorInfo(message, errorMessage, message);
       } else {
           return new ErrorInfo(defaultErrorCode, e.getMessage(), defaultErrorCode);
       }
   }
   
   private Exception tryToFindServiceException(Exception oriEx) {
       Exception e = oriEx;
       while (e != null && !(e instanceof ServiceException)) {
           e = (Exception) e.getCause();
       }
       if (e == null) {
           return oriEx;
       } else {
           return e;
       }
   }
	
}
