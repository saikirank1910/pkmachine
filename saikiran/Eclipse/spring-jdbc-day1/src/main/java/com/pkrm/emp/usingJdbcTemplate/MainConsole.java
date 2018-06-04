package com.pkrm.emp.usingJdbcTemplate;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainConsole {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
		EmployeeService employeeService = (EmployeeService) applicationContext.getBean("employeeService");

		// retreiving data from database emp table
		employeeService.getAllDetailsOfEmployee();
		System.out.println("----------------------------------------------------");
		
		
		// updating a record with 'name' field
		employeeService.updateName(4233, "kataram sai kiran");
		System.out.println("----------------------------------------------------");
		
		
		// inserting a particular record

		Employee employee = new Employee();
		employee.setId(400);
		employee.setAge(22);
		employee.setName("kataram");
		employee.setDeptId(10);
		employeeService.insertIntoEmpTable(employee);
		System.out.println("----------------------------------------------------");
		
		
		// deleting a record from database
		employeeService.deleteRow(400);
		System.out.println("----------------------------------------------------");
		
		
		// get only a particular record by id
		employeeService.getEmpById(4233);
		System.out.println("----------------------------------------------------");

		
		
		// getting data with joins combining employee and department table
		employeeService.getDataWithJoin();
		System.out.println("----------------------------------------------------");
		
		
		//inserting data using batch command
		ArrayList<Employee> list = new ArrayList<Employee>();
		for(int i=110;i<500;i++) {
			Employee employee2 = new Employee();
			employee2.setAge(20);
			employee2.setDeptId(10);
			employee2.setName("hello");
			employee2.setId(i);
			list.add(employee2);
		}
		
		employeeService.insertUsingBatchService(list);
	}
}