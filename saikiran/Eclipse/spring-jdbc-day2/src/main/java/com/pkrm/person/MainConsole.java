package com.pkrm.person;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

import com.pkrm.person.service.PersonService;

public class MainConsole {
	// I have done this assignment based on (9-Feb-18 to 10-Feb-18) weekend
	// assignment.
	/** please look at the pic in resources to understand the problem **/
	/*
	 * Note: Please note that you are NOT developing this screen in this week's
	 * assignment, you are only going to develop Spring Beans in java to support
	 * operations done from this screen.
	 * 
	 * Create Tables in Oracle to store the data for the following screen. (User
	 * table, Role Table, UserRoleMap table) Develop Spring DAOs and Spring Beans
	 * (Services) to support operations for the following screen using Spring JDBC
	 * (Spring JdbcTemplate etc..).
	 */

	public static void main(String[] args) {

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
		PersonService personService = (PersonService) applicationContext.getBean("personService");

		// inserting into person record and user role map table
		Person person = new Person();
		RoleTable roleTable = new RoleTable();
		person.setRole(roleTable);
		person.setUserid(1);
		person.setFirstName("sai kiran");
		person.setLastName("kataram");
		person.getRole().setName("developer");
		try {
			personService.createPerson(person);
		} catch (DataAccessException e) {
			System.out.println("error while inserting into person table..!" + e.getMessage());
		}
		try {
			personService.insertIntoRole();
		} catch (DataAccessException e) {
			System.out.println("error while inserting into usermaprole table..!" + e.getMessage());
		}

		// editing the person record

		person.setRole(roleTable);
		person.setUserid(1);
		person.setFirstName("SK");
		person.setLastName("kataram");
		try {
			personService.editPerson(person);
		} catch (DataAccessException e) {
			System.out.println("error while editing the person table..!" + e.getMessage());
		}

		// deleting the person record

		try {
			personService.deletePerson(1);
			
		} catch (DataAccessException e) {
			System.out.println("error while deleting the person table..!" + e.getMessage());
		}
		
		//inserting data using batch batch
		
		ArrayList<Person> list = new ArrayList<Person>();
		for(int i=10;i<15;i++) {
			person.setRole(roleTable);
			person.setUserid(i);
			person.setFirstName("abcd");
			person.setLastName("qweq");
			person.setRole(roleTable);
			person.getRole().setName("manager");
			list.add(person);
		}
		try {
			personService.insertUsingBatch(list);
		} catch (DataAccessException e) {
			System.out.println("error while using update batch" + e.getMessage());
		}
	}
}
