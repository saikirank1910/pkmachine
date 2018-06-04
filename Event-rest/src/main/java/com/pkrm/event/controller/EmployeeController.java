package com.pkrm.event.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pkrm.event.model.Employee;
import com.pkrm.event.service.EmployeeService;

@RestController
@RequestMapping(value="/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value="/getAllEmployees" ,method=RequestMethod.GET)
	public ResponseEntity<List<Employee>> getAllEmployees(){
		return new ResponseEntity<List<Employee>>(employeeService.getAllEmployees(),HttpStatus.OK);
	}
}
