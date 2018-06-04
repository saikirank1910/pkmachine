package com.pkrm.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pkrm.event.dao.EmployeeDao;
import com.pkrm.event.model.Employee;


@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeDao employeeDao;
	
	public List<Employee> getAllEmployees() {
		return employeeDao.getAllEmployees();
	}

}
