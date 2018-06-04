package com.weekend.assignment3;

import java.util.Scanner;

public class Hello {
	public static void main(String[] args) {
		int arr[]=new int[100];
		int temp[]=new int[100];
		int sum=0;
		Scanner read=new Scanner(System.in);
		for(int i=0;i<10;i++) {
			arr[i]=read.nextInt();
		}
		for(int i=0;i<100;i++) {
			temp[i]=0;
		}
		
		for(int i=0;i<10;i++) {
			temp[arr[i]]=temp[arr[i]]+1;
		}
		for(int i=0;i<100;i++) {
			if(temp[i]>1) {
				sum++;
			}
		}
		System.out.println(sum);
	}
}
