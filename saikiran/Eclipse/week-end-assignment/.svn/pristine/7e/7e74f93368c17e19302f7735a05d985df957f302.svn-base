package com.weekend.assignment3;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class FileOperations {
	public void add(BufferedReader readData) {
		Scanner read = new Scanner(System.in);
		TreeMap<String,String> treeMap=new TreeMap<String,String>();
		  String line,key="",value="";
		    try {
				while ((line = readData.readLine()) != null) {
				  // System.out.println(line);
					if(line.contains("!")) {
				    	treeMap.put(key,value);
				    	key=readData.readLine();
				    	value="";
				    	//System.out.println(value);
				    }
				    else {
				    	value=value+line+"\n";
				    }				        	
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    //System.out.println(value);
		    treeMap.put(key,value);
		    for(Map.Entry print:treeMap.entrySet())
		    {  
		    	System.out.println("enter something to print Task details");
		    	String ch;
		    	ch=read.nextLine();
		    	System.out.println(print.getValue());  
		    } 
	}
}
