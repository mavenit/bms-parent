package com.bms.eai.portal.lib.assement.test;

public class Food extends MainClassTest {

	String value; 
	
	public Food (String value) {
		this.value = value;
		//System.out.println("My name is : "+value);
	}
	
	protected void serveFood() {
		
		System.out.println("I'm serving "+value);
	}
	
}
