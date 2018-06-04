package com.weekend.assignment5.mainconsole;

public class AgeValidator {
	public int checkAgeForMainTask(int age) {
		if(age<30)
			return 0;
		return 1;
	}
	public int checkAgeForSubTask(int age) {
		if(age<20 || age>30)
			return 0;
		return 1;
	}
}
