package com.pkrm.person.MainConsole;

import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pkrm.person.Person;
import com.pkrm.person.RoleTable;

public class MainConsole {

	final static Logger logger = Logger.getLogger(MainConsole.class);

	public static void main(String[] args) {

		// DEPLOY THIS PROJECT IN THE TOMCAT AND RUN THIS MAIN CONSOLE
		
		final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("producer-context.xml");
		Person person = new Person();
		RoleTable role = new RoleTable();
		Producer obj = (Producer) context.getBean("producer");
		Scanner read = new Scanner(System.in);
		Scanner readInteger = new Scanner(System.in);

		logger.info("Enter the user id:");
		person.setUserid(readInteger.nextInt());

		logger.info("Enter username :");
		person.setUserName(read.nextLine());

		logger.info("Enter firstname :");
		person.setFirstName(read.nextLine());

		logger.info("Enter lastname :");
		person.setLastName(read.nextLine());

		person.setRole(role);

		logger.info("enter the role of the person (lead/developer/tester)");

		person.getRole().setName(read.nextLine());
		obj.sendMessage(person);

		context.close();
	}
}
