package com.bms.eai.lib;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class TestLib {

	public TestLib() {
	/*	String dt = DateUtils.format(new Date(), CmnConstants.GEN_REQID_FORMAT);
		System.out.println(dt);
		System.out.println(UserStatus.ACTIVATED);
		System.out.println(UserStatus.ACTIVATED.statusCode());*/
		
		
		String regex = "^[a-zA-Z]+$";
		String data = "werqwerqwerqwzxcyukw";
		System.out.println(data.matches(regex));
		
		
		SimpleDateFormat timeFormat = new SimpleDateFormat(DateUtils.TIME_FORMAT);
		String formattedDate = timeFormat.format(new Date());
		System.out.println(formattedDate);
		
		LocalDate today = LocalDate.now();
	    System.out.println("Current date :" + today);
	} 

	public static void main(String[] args) {
		 new TestLib();
	}

}
