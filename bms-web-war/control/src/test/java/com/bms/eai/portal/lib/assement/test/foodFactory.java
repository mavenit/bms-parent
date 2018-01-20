package com.bms.eai.portal.lib.assement.test;

public class foodFactory extends MainClassTest{

	public Food getFood(String value) {
		return new Food(value);
	}
	
}
