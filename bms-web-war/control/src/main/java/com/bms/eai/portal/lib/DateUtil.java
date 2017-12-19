package com.bms.eai.portal.lib;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.Years;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bms.eai.portal.constants.CmnConstants;

/**
 * @author kul_sudhakar
 *
 */
public class DateUtil {

	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
	
    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(CmnConstants.DT_DD_MM_YYYY_Slash);
        Date uDate = new Date();
        return sdf.format(uDate);
    }
    
   
    public static int compareDates(Date startDate, Date endDate) {
    	if(startDate.before(endDate))
    		return -1;
    	else if(startDate.after(endDate))
    		return 1;
    	else
    		return 0;	  
    }
    
    public static String getStr(Object obj) {
        if (obj != null) {
        	
            return obj.toString().trim();
        } else {
            return "";
        }
    }
    
    public static int getDateDiffJodaTimeInYears(String startDate, String endDate) {
    	return getDateDiffJodaTimeInYears(startDate, endDate, CmnConstants.DT_DD_MM_YYYY_Slash);
    }
    
    public static int getDateDiffJodaTimeInYears(String startDate, String endDate, String pattern) {
    	int diff = 0;
    	try {
	    	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	    	Date d1 = sdf.parse(startDate);
	    	Date d2 = sdf.parse(endDate);
	    	DateTime dt1 = new DateTime(d1);
			DateTime dt2 = new DateTime(d2);
			diff = Years.yearsBetween(dt1, dt2).getYears();
    	} catch (Exception e) {
    	}
    	return diff;
    }
    
    public static String getPeriodYearMonthDay(Date date) {
    	DateTime endDt = new DateTime(new Date());
    	DateTime startDt = new DateTime(date);
    	Period p = new Period(endDt, startDt);
		String period = p.getYears() + "y" + p.getMonths() + "m" + p.getDays() + "d";
		return period.replace("-", "");
    }
    
    public static int getDateDiffJodaTimeInYears(Date startDate) {
    	return getDateDiff(startDate, new Date(), CmnConstants.YEAR);
    }
    
    public static int getDateDiffJodaTimeInYears(Date startDate, Date endDate) {
    	return getDateDiff(startDate, endDate, CmnConstants.YEAR);
    }
    
    public static int getDateDiffJodaTimeInMonths(String startDate, String endDate) {
    	return getDateDiffJodaTimeInMonths(startDate, endDate, CmnConstants.DT_DD_MM_YYYY_Slash);
    }
    
    public static int getDateDiffJodaTimeInMonths(String startDate, String endDate, String pattern) {
    	int diff = 0;
    	try {
	    	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	    	Date d1 = sdf.parse(startDate);
	    	Date d2 = sdf.parse(endDate);
	    	DateTime dt1 = new DateTime(d1);
			DateTime dt2 = new DateTime(d2);
			diff = Months.monthsBetween(dt1, dt2).getMonths();
    	} catch (Exception e) {
    	}
    	return diff;
    }
    
    public static Date addYearMonthDaysToDate(int year, int month, int day) {
    	return addYearMonthDaysToDate(null, year, month, day);
    }
    
    public static Date addYearMonthDaysToDate(Date date, int year, int month, int day) {
    	logger.debug("addYearMonthDaysToDate >>> Date: " + date + " - Year: " + year + " - Month: " + month + " - Day: " + day);
    	if(date != null) {
    		date = new Date();     		
    		logger.debug("addYearMonthDaysToDate >>> Date is null, get current date: " + date);
    	}
    	DateTime dt = new DateTime(date).plusYears(year).plusMonths(month).plusDays(day);
    	logger.debug("addYearMonthDaysToDate >>> New Date: " + dt.toString());
    	return dt.toDate();
    }
    
    public static Date minusYearMonthDaysToDate(int year, int month, int day) {
    	return minusYearMonthDaysToDate(null, year, month, day);
    }
    
    public static Date minusYearMonthDaysToDate(Date date, int year, int month, int day) {
    	logger.debug("minusYearMonthDaysToDate >>> Date: " + date + " - Year: " + year + " - Month: " + month + " - Day: " + day);
    	if(date != null) {
    		date = new Date();     		
    		logger.debug("minusYearMonthDaysToDate >>> Date is null, get current date: " + date);
    	}
    	DateTime dt = new DateTime(date).minusYears(year).minusMonths(month).minusDays(day);
    	logger.debug("minusYearMonthDaysToDate >>> New Date: " + dt.toString());
    	return dt.toDate();
    }
    
    public static Date addHourMinutesSecondToDate(int hours, int minutes, int seconds) {
    	return addHourMinutesSecondToDate(null, hours, minutes, seconds);
    }
    
    public static Date addHourMinutesSecondToDate(Date date, int hours, int minutes, int seconds) {
    	if(date != null) {
    		date = new Date();
    	}
    	DateTime dt = new DateTime(date).plusHours(hours).plusMinutes(minutes).plusSeconds(seconds);
    	return dt.toDate();
    }
    
    public static Date minusHourMinutesSecondToDate(int hours, int minutes, int seconds) {
    	return minusHourMinutesSecondToDate(null, hours, minutes, seconds);
    }
    
    public static Date minusHourMinutesSecondToDate(Date date, int hours, int minutes, int seconds) {
    	if(date != null) {
    		date = new Date();
    	}
    	DateTime dt = new DateTime(date).minusHours(hours).minusMinutes(minutes).minusSeconds(seconds);
    	return dt.toDate();
    }
    
    public static int getDateDiffJodaTimeInMonths(Date date) {
    	return getDateDiffJodaTimeInMonths(new Date(), date);
    }
    
    public static int getDateDiffJodaTimeInMonths(Date startDate, Date endDate) {
    	return getDateDiff(startDate, endDate, CmnConstants.MONTH);
    }
    
    public static int getDateDiffJodaTimeInDays(String startDate, String endDate) {
    	int diff = 0;
    	try {
	    	SimpleDateFormat sdf = new SimpleDateFormat(CmnConstants.DT_DD_MM_YYYY_Slash);
	    	Date d1 = sdf.parse(startDate);
	    	Date d2 = sdf.parse(endDate);
			diff = getDateDiffJodaTimeInDays(d1, d2);
    	} catch (Exception e) {}
    	return diff;
    }
    
    public static int getDateDiffJodaTimeInDays(Date startDate, Date endDate) {
    	return getDateDiff(startDate, endDate, CmnConstants.DAY);
    }
    
    private static int getDateDiff(Date startDate, Date endDate, String type) {
    	int diff = 0;
    	try {
	    	DateTime dt1 = new DateTime(startDate);
			DateTime dt2 = new DateTime(endDate);
			if(type.equalsIgnoreCase(CmnConstants.DAY)) {
				diff = Days.daysBetween(dt1, dt2).getDays();
			} else if(type.equalsIgnoreCase(CmnConstants.MONTH)) {
				diff = Months.monthsBetween(dt1, dt2).getMonths();
			} else if(type.equalsIgnoreCase(CmnConstants.YEAR)) {
				diff = Years.yearsBetween(dt1, dt2).getYears();
			}
    	} catch (Exception e) {}
    	return diff;
    }
    
    public static String getFormatDateWithSlash(Date date) {
    	return getFormatDate(date, CmnConstants.DT_DD_MM_YYYY_Slash);
    }
    
    public static Date getFormatDate(String date, String pattern) throws ParseException {
    	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        if(date!=null){
           return sdf.parse(date);
        }else{
            return null;
        }
    }
    
    public static String getFormatDate(Date date, String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        if(date!=null){
           return sdf.format(date);
        }else{
            return "";
        }
    }
    
    public static Timestamp getSQLTimestamp() {
    	Calendar calendar = Calendar.getInstance();
    	java.util.Date now = calendar.getTime();
    	java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
    	
    	return currentTimestamp;
    }
    
    public static java.util.Date addDayToTimestamp(Date date, Integer addDay){       
        
		 Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, addDay);
			
			return cal.getTime();
	    }
    
    public static XMLGregorianCalendar getXMLGregorianCalendar(){
    	try {
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
			return null;
		}
    }
    
    public static Calendar toCalendar(final Date date){
    	final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
    
    public static Calendar toCalendar(final long millis) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar;
    }
    
    public static XMLGregorianCalendar toXmlGregorianCalendar(final Date date) {
        return toXmlGregorianCalendar(date.getTime());
    }
    
    public static XMLGregorianCalendar toXmlGregorianCalendar(final long date) {
        try {
            final GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTimeInMillis(date);
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        }
        catch (final DatatypeConfigurationException ex) {
            System.out.println("Unable to convert date '%s' to an XMLGregorianCalendar object");
            return null;
        }
    }
    
    public static XMLGregorianCalendar getXMLGregorianCalendar(Timestamp timestamp) {
        try {
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTimeZone(TimeZone.getTimeZone("GMT+02:00"));
            gc.setTimeInMillis(timestamp.getTime());
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
        } catch (Exception ex) {
           return null;
        }
    }
    
    public static String getFormatBigEndianTimeWithDash(Date date){
        String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
                SimpleDateFormat sdf =  new SimpleDateFormat(DATE_FORMAT);
                if(date!=null){
                   return sdf.format(date);
                }else{
                    return "";
                }
    }
    
    public static String getFormatBigEndianWithDash(Date date){
        String DATE_FORMAT = "yyyy-MM-dd";
                SimpleDateFormat sdf =  new SimpleDateFormat(DATE_FORMAT);
                if(date!=null){
                   return sdf.format(date);
                }else{
                    return "";
                }
    }
 
    public static String getFormatBigEndian(Date date){
        String DATE_FORMAT = "yyyyMMdd";
                SimpleDateFormat sdf =  new SimpleDateFormat(DATE_FORMAT);
                if(date!=null){
                   return sdf.format(date);
                }else{
                    return "";
                }
    }
    
    public static String getFormatBigEndianWithSlash(Date date){
        String DATE_FORMAT = "dd/MM/yyyy";
                SimpleDateFormat sdf =  new SimpleDateFormat(DATE_FORMAT);
                if(date!=null){
                   return sdf.format(date);
                }else{
                    return "";
                }
    }
    public static java.util.Date convertStrDateToDate(String strDate){       
    	return convertStrDateToDate(strDate, "");
    } 
    
    public static java.util.Date convertStrDateToDate(String strDate, String formatStrDate){    
    	
	 	if (getStr(formatStrDate).equals("")) formatStrDate = "yyyy-MM-dd";
	     SimpleDateFormat sdfStrDate = new SimpleDateFormat(formatStrDate);
	     java.util.Date date = new Date();
	     try {
	     	date = sdfStrDate.parse(strDate);
	         return date;
	     } catch (Exception e) {
	         System.out.println(e.getMessage());
	         return null;
	     }
    }
    
    public static String getCurrentYear() {
    	return String.valueOf(new DateTime().getYear());
    }
    
    public static Timestamp convertDate2SqlTimeStamp(Date date) {
	    Timestamp sq = new Timestamp(date.getTime());
	    return sq;
    }
   
    public static Date convertTimestampToDate(Timestamp timestamp) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTimeInMillis(timestamp.getTime());
    	return calendar.getTime();
    }
 
       
    public static boolean checkBday45y1d(String bday, String compDate, String pattern) {
		Integer month = 0, day = 0;
		try {
	    	DateTime dt1 = new DateTime(getFormatDate(bday, pattern));
			DateTime dt2 = new DateTime(getFormatDate(compDate, pattern));
			Period p = new Period(dt1, dt2, PeriodType.yearMonthDayTime());
			System.out.println("Year: " + p.getYears());
			System.out.println("MOnth: " + p.getMonths());
			System.out.println("Day: " + p.getDays());
			month = p.getMonths();
			day = p.getDays();
    	} catch (Exception e) {}
		
		if(month != 0 || day != 0) {
			return true;
		}
		return false;
	}
    
    public static boolean isWeekend(Date date){
		
		   Calendar cal = Calendar.getInstance();
		   cal.setTime(date);
		    if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
		       return true;
		    
		 return false;   
	}
    //get list of saturdays and sundays in given dates.
    public static List<Date> getSatAndSundays(Date fromDate, Date toDate){
    	 List<Date> datesLst = new ArrayList<Date>();
    	Calendar cal1 = Calendar.getInstance();
		cal1.setTime(fromDate);
		
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(toDate);
		cal2.add(Calendar.DATE, 1);
		
		 while(cal1.before(cal2)){
		        if((cal1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)){
		        	System.out.println(cal1.getTime());
		        	datesLst.add(cal1.getTime());
		        }
		        cal1.add(Calendar.DATE, 1);
		    }
		 
		 return datesLst;
    }
	

    public static void main(String[] args) throws ParseException {
    	System.out.println("started");
    	//Date todate = convertStrDateToDate("2015-10-10");
    	/*SimpleDateFormat sdf = new SimpleDateFormat(BaseConstants.DT_DD_MM_YYYY_Slash);
    	String bdt = "26/07/1970";
    	DateTime dt = new DateTime(new Date());
    	DateTime dt1 = new DateTime();
		try {
			dt1 = new DateTime(sdf.parse(bdt));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Period p = new Period(dt1, dt);
		String period = p.getYears() + "y" + p.getMonths() + "m" + p.getDays() + "d";
		System.out.println(period);
		int diff = Months.monthsBetween(dt, dt1).getMonths();
		System.out.println("Months :: " + diff);
    	System.out.println(dt.getYear());
    	System.out.println(dt.getDayOfMonth());
    	System.out.println(dt.getMonthOfYear());*/
    	/*Date expireDt = addYearMonthDaysToDate(0, 18, 6);
		Date blinkDt = addYearMonthDaysToDate(0, 19, 0);
		int expireDtDiffDays = getDateDiffJodaTimeInDays(expireDt, getFormatDate("28-2-2017", BaseConstants.DT_DD_MM_YYYY_Dash));
		System.out.println("expireDtDiffDays >>> " + expireDtDiffDays);
		int blnkDtDiffDays = getDateDiffJodaTimeInDays(expireDt, blinkDt);
		System.out.println("blnkDtDiffDays >>> " + blnkDtDiffDays);
		if(expireDtDiffDays >=0 && expireDtDiffDays <= blnkDtDiffDays) {
			System.out.println("TEXT BLINK!!!");
		}*/
		
	}
	
}
