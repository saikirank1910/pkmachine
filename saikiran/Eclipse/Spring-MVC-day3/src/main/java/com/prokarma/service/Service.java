package com.prokarma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prokarma.dao.EmployeeDao;
import com.prokarma.pojo.Employee;

@Component
public class Service {
	@Autowired
	private EmployeeDao employeeDao;

	public List<Employee> getList() {
		return employeeDao.getList();
	}

	public void addEmp(Employee employee) {
		employeeDao.addEmployee(employee);
	}

	public Employee getEmpById(int id) {
		List<Employee> list= employeeDao.getEmpById(id);
		Employee employee =list.get(0); 
		return employee;
	}

	public void deleteEmp(int id) {
		employeeDao.deleteEmp(id);
	}
	public void updateEmpById(Employee employee) {
		employeeDao.updateEmp(employee);
	}
}
