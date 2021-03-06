package com.bms.eai.portal.constants;

import java.io.File;

/**
 * @author kul_sudhakar
 *
 */
public class CmnConstants {

public static final Boolean useRestService = true; // Back-end Service - return false if using SOAP
	
	public static final String EMPTY_STRING 				= "";
	public static final String SPACE 						= " ";
	public static final String COMMA 						= ",";
	public static final String LEFT_PARANTHESES				= "{";
	public static final String RIGHTT_PARANTHESES			= "}";
	public static final String LEFT_BRACE 					= "(";
	public static final String RIGHT_BRACE 					= ")";
	
	public static final Integer ZERO 						= 0;
	
	public static final String DIGIT_0 						= "0";
	public static final String DIGIT_1 						= "1";
	public static final String DIGIT_2 						= "2";
	public static final String DIGIT_3 						= "3";
	
	public static final String YES_SHORT					= "Y";
	public static final String NO_SHORT						= "N";
	
	public static final String YES_FULL 					= "YES";
	public static final String NO_FULL 						= "NO";
	public static final String OPEN							= "Open";
	public static final String CLOSE						= "Close";
	
	public static final String DAY							= "DAY";
	public static final String MONTH						= "MONTH";
	public static final String YEAR							= "YEAR";
	
	public static final String NULL 						= "NULL";
	
	public static final String BEAN_SCOPE_PROTOTYPE 		= "prototype";
		
	public static final String DT_DD_MM_YYYY_Dash			= "dd-MM-yyyy";
	public static final String DT_DD_MM_YYYY_Slash			= "dd/MM/yyyy";
	
	public static final String PATH_PROJ_CONFIG				= "project.config.path";
	public static final String PATH_CATALINA_HOME			= "catalina.home";
	public static final String PATH_CATALINA_BASE			= "catalina.base";
	public static final String PROJ_JBOSS_HOME				= "jboss.server.config.dir";
	public static final String PROPERTY_FILENAME			= "portal";
	public static final String PROPERTIES_EXT				= ".properties";
	public static final String FILE_SYS_RESOURCE			= File.separator + PROPERTY_FILENAME + PROPERTIES_EXT;
	public static final String PROPERTY_CLASSPATH			= "classpath:" + PROPERTY_FILENAME;
	public static final String FILE_PFX						= "file:";
	
	public static final String MAV_ALERT_TYPE = "alertType";
	public static final String MAV_ALERT_MSG = "alertMessage";
	
	public static final String MAV_ERROR_CODE = "error";
	public static final String MAV_ERROR_INFO = "info";
	public static final String MAV_ERROR_WARN = "warning";
	public static final String MAV_SUCCESS = "success";
	
	public static final String MAV_SUCCESS_MSG_ID = "ID ";
	public static final String MAV_SUCCESS_PERSIST_MSG = "Successfully save the record";
	public static final String MAV_SUCCES_VIEW_MSG = "View the record";
	public static final String MAV_SUCCESS_UPDATE_MSG = "Successfully update the record";
	public static final String MAV_SUCCESS_DELETE_MSG = "Successfully delete the record";
	public static final String MAV_ERROR_MSG = "Error occured during processing transaction,Kindly contact system admin!";
	
	
}
