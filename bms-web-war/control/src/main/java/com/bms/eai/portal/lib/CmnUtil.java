package com.bms.eai.portal.lib;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.bms.eai.portal.constants.CmnConstants;

/**
 * @author kul_sudhakar
 *
 */
public class CmnUtil {

	public static String getStr(Object obj) {
        if (obj != null) {
            return obj.toString().trim();
        } else {
            return CmnConstants.EMPTY_STRING;
        }
    }
    
   
    public static String getStrWithNull(Object obj) {
        if (obj != null) {
            return obj.toString().trim();
        } else {
            return null;
        }
    }
    
   
    public static String getStrUpper(Object obj) {
        if (obj != null) {
            return obj.toString().trim().toUpperCase();
        } else {
            return CmnConstants.EMPTY_STRING;
        }
    }
    
   
    public static String getStrUpperWithNull(Object obj) {
        if (obj != null && !getStr(obj).equalsIgnoreCase("NULL")) {
            return obj.toString().trim().toUpperCase();
        } else {
            return null;
        }
    }
    
    public static String getStrLower(Object obj) {
        if (obj != null) {
            return obj.toString().trim().toLowerCase();
        } else {
            return CmnConstants.EMPTY_STRING;
        }
    }
    
  
    public static String getStrTitle(Object obj) {
    	 if (!isObjNull(obj)) {
    		 String modStr = obj.toString().trim().toLowerCase();
    		 String[] strArr = modStr.split(CmnConstants.SPACE);    	
    		 String newStr = CmnConstants.EMPTY_STRING;
    		 for (int i = 0; i < strArr.length; i++) {
    			 strArr[i] = Character.toUpperCase(strArr[i].charAt(0)) + strArr[i].substring(1);
    			 newStr+= strArr[i] + CmnConstants.SPACE;
    		 }
             return newStr.trim();
         } else {
             return CmnConstants.EMPTY_STRING;
         }
    }
    
    public static Integer getInt(Object obj) {
        if (obj != null) {
        	try {
        		return Integer.parseInt(obj.toString().trim());
        	} catch(Exception e){
        		return CmnConstants.ZERO;
        	}
        } else {
            return CmnConstants.ZERO;
        }
    }
    
  
	public static Double getDouble(Object obj) {
		return getDouble(obj, null);
	}
    
 
	public static Double getDouble(Object obj, String pattern) {
		if (obj != null) {
			try {
				double d = Double.parseDouble(obj.toString().trim());
				
				if (!isObjNull(pattern)) {
					DecimalFormat df = new DecimalFormat(pattern);
					return Double.parseDouble(df.format(d));
				} else {
					return d;
				}
			} catch (Exception e) {
				return (double) 0;
			}
		} else {
			return (double) 0;
		}
	}

    public static BigDecimal getBigDecimal(Object input) 
    {  
        try {
        	if (input instanceof String) 
        		return new BigDecimal((String) input);
        	else if (input instanceof Double) 
        		return new BigDecimal((Double) input);
        	else if (input instanceof Integer) 
        		return new BigDecimal((Integer) input);
        	else if (input instanceof Long) 
        		return new BigDecimal((Long) input);
        	else if (input instanceof BigInteger) 
        		return new BigDecimal((BigInteger) input);
        	else if (input instanceof Float) 
        		return new BigDecimal((Float) input);
        	else if (input instanceof Short) 
        		return new BigDecimal((Short) input);
        	else if(input instanceof BigDecimal)
        		return (BigDecimal) input;
        	else
        		return new BigDecimal(CmnConstants.ZERO);
        } catch (Exception e) {
            return new BigDecimal(CmnConstants.ZERO);
        }		
    }
	
	public static Integer getListSize(List<?> o) {
        if (o != null && o.size() > 0) {
            return o.size();
        } else {
            return CmnConstants.ZERO;
        }
    }

    public static Boolean isListNull(List<?> o) {
        if (o != null && getListSize(o) > 0) {
            return false;
        } else {
            return true;
        }
    }
    
    public static Boolean isListZero(List<?> o) {
        if (!isListNull(o) && o.size()==0 ) {
            return true;
        } else {
            return false;
        }
    }
	
	public static boolean isEquals(String oriSrc, String compareSrc) {
        if (oriSrc != null && getStr(oriSrc).equals(getStr(compareSrc))) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEqualsCaseIgnore(String oriSrc, String compareSrc) {
        if (oriSrc != null && getStr(oriSrc).equalsIgnoreCase(getStr(compareSrc))) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean isEqualsAny(String oriSrc, String compareSrc) {
    	boolean isEqual = false;
    	String compareSrcArr[] = compareSrc.split(",");
    	for (int i = 0; i < compareSrcArr.length; i++) {
    		 if (oriSrc != null && getStr(oriSrc).equals(getStr(compareSrcArr[i]))) {
    			 isEqual = true;
    			 break;
    	       }
		}
    	
    	return isEqual;
    }
    
    public static boolean isEqualsCaseIgnoreAny(String oriSrc, String compareSrc) {
    	boolean isEqual = false;
    	String compareSrcArr[] = compareSrc.split(",");
    	for (int i = 0; i < compareSrcArr.length; i++) {
    		 if (oriSrc != null && getStr(oriSrc).equalsIgnoreCase(getStr(compareSrcArr[i]))) {
    			 isEqual = true;
    			 break;
    	       }
		}
    	
    	return isEqual;
    }
	
    public static boolean isObjNull(Object obj) {
        if (obj != null && getStr(obj).length() > 0) {
            return false;
        } else {
            return true;
        }
    }
    
    public static Boolean isMaxNoReached(Integer supplyNo, Integer compareNo) {
        return (supplyNo > compareNo) ? true : false;
    }
    
  
    public static String subString(String input, int start, int end) {
		if (input != null){
		    return getStr(input).substring(start, end);	
		}
		else {
		    return CmnConstants.EMPTY_STRING;
		}
    }
    
  
    public static String subString(String input, int start) {
		if (input != null){
		    return getStr(input).substring(start);	
		}
		else {
		    return CmnConstants.EMPTY_STRING;
		}
    }
    
    
	public static boolean isValidEmailAddress(String email) {
		boolean result = false;
		if (!isObjNull(email))
			result = email.matches("/[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}/");
		return result;
	}
	
	
	public static java.sql.Timestamp getCurrentDateSQL() {
        
    	Calendar calendar = Calendar.getInstance();
    	java.util.Date now = calendar.getTime();
    	java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
    	
    	return currentTimestamp;
    }
	
	public static java.sql.Timestamp convertDateToSQLDate(Date date) {
        
    	//Calendar calendar = Calendar.getInstance();
    	//java.util.Date now = calendar.getTime();
    	java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(date.getTime());
    	
    	return currentTimestamp;
    }
	
	public static java.sql.Timestamp getRecordModfiedDate() {
    	return getRecordCreateDate();
    }
	
	public static java.sql.Timestamp getRecordCreateDate() {        
    	Calendar calendar = Calendar.getInstance();
    	java.util.Date now = calendar.getTime();
    	java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());    	
    	return currentTimestamp;
    }
	
	public static Date convertDate(String date, String pattern){
    	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    	try {
	        if(date!=null){
	           return sdf.parse(date);
	        }else{
	            return null;
	        }
    	} catch(ParseException e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    public static String convertDate(Date date, String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        if(date!=null){
           return sdf.format(date);
        }else{
            return "";
        }
    }
	
}
