package com.bms.eai.portal.lib.assement.test;

public class MainClassTest {

	 public static void main(String[] args) {
		/*foodFactory t =new foodFactory();
		Food f1 = t.getFood("Fastfood");
		Food f2 = t.getFood("Fruits");
		//System.out.println(t.string() +" "+s.string());
		System.out.println("My name is : "+f1.getClass().getName());
		System.out.println("My name is : "+f2.getClass().getName());
		System.out.println("our superclass :"+f1.getClass().getSuperclass().getName());
		f1.serveFood();
		f2.serveFood();*/
		 
		 int i=12345;
		 int j= 6789;
		 
		 String s= String.valueOf(i);
		 String ss= String.valueOf(j);
		 String sss= s.concat(ss);
		 System.out.println(sss);
		 
		 for(int k=0;k<sss.length();k++){
			 System.out.print(sss.length()-k);
		 }
		 
	}
}
