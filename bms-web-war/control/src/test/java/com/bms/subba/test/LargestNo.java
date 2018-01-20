package com.bms.subba.test;

public class LargestNo {

	public static void main(String[] args) {
		 
		int max;
		int a[] = new int[] {12,4,10,56,78,95,43,85,101};
        max = a[0];
        for(int i = 0; i < a.length; i++)
        {
            if(max > a[i])
            {
                max = a[i];
            }
        }
        System.out.println("Maximum value:"+max);
		 

	}

}
