package com.bms.subba.test;

public class SecondLargestNo {

	public static void main(String[] args) {
		int arr[] = { 14, 46, 47, 86, 92, 52, 48, 36, 66, 99,100 };
		int largest = arr[0];
		int secondLargest = arr[0];
		//System.out.println("The given array is:" );
		for (int i = 0; i < arr.length; i++) {
 
			if (arr[i] > largest) {
				secondLargest = largest;
				largest = arr[i];
 
			} else if (arr[i] > secondLargest) {
				secondLargest = arr[i];
 
			}
		}
 
		System.out.println("Second largest number is:" + secondLargest);
 

	}

}
