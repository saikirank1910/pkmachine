package com.pkrm.event.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pkrm.event.model.EmployeeInformation;
import com.pkrm.event.service.EmployeeInformationService;

@RestController
@RequestMapping(value="/employee")
public class EmployeeInformationController {
	
	@Autowired
	private EmployeeInformationService employeeInformationService;
	
	@RequestMapping(value="/saveEmployeeEventDetails" ,method=RequestMethod.POST)
	public ResponseEntity<String> saveEmployeeEventDetails(@RequestBody List<EmployeeInformation> list){
		System.out.println(list);
		int result = employeeInformationService.saveEmployeeEventDetails(list);
		if(result==1) {
		return new ResponseEntity<String>("success",HttpStatus.OK);
		}
		return new ResponseEntity<String>("failure",HttpStatus.OK);
	}
}
