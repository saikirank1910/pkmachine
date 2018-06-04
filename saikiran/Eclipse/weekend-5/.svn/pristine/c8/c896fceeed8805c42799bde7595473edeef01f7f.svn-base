package com.weekend.assignment5.mainconsole;

import static org.junit.Assert.*;

import org.junit.Test;

public class AgeValidatorTest {

	@Test
	public void testCheckAgeForMainTask() {
		AgeValidator mainTaskAgeValidator = new AgeValidator();
		int result = mainTaskAgeValidator.checkAgeForMainTask(33);
		assertEquals(1,result);
	}

	@Test
	public void testCheckAgeForSubTask() {
		AgeValidator subTaskAgeValidator = new AgeValidator();
		int result = subTaskAgeValidator.checkAgeForSubTask(22);
		assertEquals(1,result);
	}
	@Test
	public void testingAgeForMainTaskWhichIsInvalid() {
		AgeValidator mainTaskAgeValidator = new AgeValidator();
		int result = mainTaskAgeValidator.checkAgeForMainTask(29);
		assertEquals(0,result);
	}

	@Test
	public void testingAgeForSubTaskWhichIsInvalid() {
		AgeValidator subTaskAgeValidator = new AgeValidator();
		int result = subTaskAgeValidator.checkAgeForSubTask(33);
		assertEquals(0,result);
	}
}
