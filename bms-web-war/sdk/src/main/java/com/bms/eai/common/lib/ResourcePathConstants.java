package com.bms.eai.common.lib;

/**
 * @author kul_sudhakar
 *
 */
public interface ResourcePathConstants {

	public static final String API_VERSION = "1.0";
	public static final String API_NAME = "api";
	
	public static final String HTTP = "http://";
	public static final String HTTP_COLUMN = ":";
	public static final String HTTP_SLASH = "/";
	
	public static final String HTTP_PROP_CONTEXT_PATH ="propertydetails";
	public static final String HTTP_STATIC_CONTEXT_PATH ="cmndetails";
	
	public static final String CREATE = "create";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";
	public static final String LOADBYID = "load";
	public static final String LOADBYPAGE = "loadpage";
	public static final String LOADBYCOUNTRY = "load/country";
	public static final String LOADBYNAME = "/load/name";
	public static final String LOADALL = "loadall";
	
	public static final String OPERATION_NAME = "CURD";
	public static final String SERVICE_NAME = "SERVICENAME";
	public static final String ID = "ID";
	public static final String NAME = "NAME";
	public static final String BEAN = "BEAN";
	
	
	// =========== CONFIGURE THE RESOURCE PATH FOR PROPERTY MASTER DETAILS =========== //
	
	public static final String PROP_RESOURCE_PATH ="propsettings";
	public static final String ASSET_RESOURCE_PATH ="propasset";
	public static final String BLOCKDETAILS_RESOURCE_PATH ="propblockdetails";
	public static final String FACILITIES_RESOURCE_PATH ="propfacilities";
	public static final String FINANCIALYEAR_RESOURCE_PATH ="propfinancialyear";
	public static final String FIXEDANNUALRECURRING_RESOURCE_PATH ="propfixedannualrecurring";
	public static final String BAYPARKING_RESOURCE_PATH ="propbayparking";
	public static final String SERVICEPROVIDER_RESOURCE_PATH ="propserviceprovider";
	
	// =========== CONFIGURE THE RESOURCE PATH FOR COMMON MODULE SERVICE DETAILS =========== //
	
	public static final String COUNTRY_RESOURCE_PATH ="country";
	public static final String STATE_RESOURCE_PATH ="state";
	public static final String PROPTYPE_RESOURCE_PATH ="proptype";
	
}
