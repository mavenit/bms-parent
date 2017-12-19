package com.bms.eai.portal.security.lib;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kul_sudhakar
 *
 */
public class DateUtils {

	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String TIME_FORMAT = "HH:mm:ss";
	public static final String DATE_TIME_FORMAT = DATE_FORMAT + " " + TIME_FORMAT;
	private static Logger logger = LoggerFactory.getLogger(DateUtils.class);

	public static Date parse(String dateStr) throws ParseException {
		return getDateFormat().parse(dateStr);
	}

	public static Date safeParse(String dateStr) {
		try {
			return parse(dateStr);
		} catch (ParseException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	public static Date ofDateTime(String dateTimeStr) throws ParseException {
		return getDateTimeFormat().parse(dateTimeStr);
	}

	public static Date ofTime(String timeStr) throws ParseException {
		return getTimeFormat().parse(timeStr);
	}

	public static String format(Date date) {
		return getDateFormat().format(date);
	}

	public static String format(Date date, String pattern) {
		return getDateFormat(pattern).format(date);
	}

	public static String formatDateTime(Date date) {
		return getDateTimeFormat().format(date);
	}

	public static String formatTime(Date date) {
		return getTimeFormat().format(date);
	}

	public static Date addYears(Date date, int numberOfYears) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, numberOfYears);
		return cal.getTime();
	}

	public static Date addHours(Date date, int numberOfHours) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, numberOfHours);
		return cal.getTime();
	}

	public static Date addDays(Date date, int numberOfDays) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, numberOfDays);
		return cal.getTime();
	}

	public static Date addSeconds(Date date, int numberOfSeconds) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, numberOfSeconds);
		return cal.getTime();
	}

	public static DateFormat getDateFormat(String pattern) {
		return new SimpleDateFormat(pattern);
	}

	public static DateFormat getDateFormat() {
		return new SimpleDateFormat(DATE_FORMAT);
	}

	public static DateFormat getTimeFormat() {
		return new SimpleDateFormat(TIME_FORMAT);
	}

	public static DateFormat getDateTimeFormat() {
		return new SimpleDateFormat(DATE_TIME_FORMAT);
	}

	public static Date today() {
		return dateOnly(now());
	}

	public static Date dateOnly(Date fromDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fromDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date now() {
		return new Date();
	}

	public static Date endOfDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

}
