package com.pkrm.emp.usingJdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

public class EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	
	public void getAllDetailsOfEmployee() {

		System.out.println("The emp details are..!");
		try {
			List<Employee> employeeDetails = employeeDao.getAllEmpDetails();
			for (int i = 0; i < employeeDetails.size(); i++) {
				System.out.print("EMP ID: " + employeeDetails.get(i).getId() + ",");
				System.out.print(" NAME: " + employeeDetails.get(i).getName() + ",");
				System.out.print(" AGE: " + employeeDetails.get(i).getAge() + ",");
				System.out.print(" DEPT ID: " + employeeDetails.get(i).getDeptId() + ",");
				System.out.println("");
			}
		} catch (DataAccessException e) {
			System.out.println("error while retrieving data" + e.getMessage());
		}
	}

	public void updateName(int id, String name) {
		try {
			int result = employeeDao.updateEmpTable(id, name);
			System.out.println(result + " no of rows have been effected while updating");
		} catch (DataAccessException e) {
			System.out.println("error at updating particular record " + e.getMessage());
		}
	}

	public void insertIntoEmpTable(Employee employeeData) {
		try {
			int result = employeeDao.insertIntoEmp(employeeData);
			System.out.println(result + " no of rows have been effected while inserting");
		} catch (DataAccessException e) {
			System.out.println("error while inserting a record " + e.getMessage());
		}
	}

	public void deleteRow(int id) {
		try {
			int result = employeeDao.deleteRecord(id);
			System.out.println(result + " no of rows have been effected while deleting");
		} catch (DataAccessException e) {
			System.out.println("error while deleting a record " + e.getMessage());
		}
	}

	public void getEmpById(int id) {
		try {
			Employee employee = employeeDao.getEmpById(id);
			System.out.print("The emp details with id "+id+" is");
			System.out.print("EMP ID: " + employee.getId() + ",");
			System.out.print(" NAME: " + employee.getName() + ",");
			System.out.print(" AGE: " + employee.getAge() + ",");
			System.out.print(" DEPT ID: " + employee.getDeptId() + ",");
			System.out.println("");
		} catch (DataAccessException e) {
			System.out.println("error while retreiving a record " + e.getMessage());
		}
	}

	public void getDataWithJoin() {
		try {
			List<Employee> employeeDetails = employeeDao.getData();

			for (int i = 0; i < employeeDetails.size(); i++) {
				System.out.print("EMP ID: " + employeeDetails.get(i).getId() + ",");
				System.out.print(" NAME: " + employeeDetails.get(i).getName() + ",");
				System.out.print(" AGE: " + employeeDetails.get(i).getAge() + ",");
				System.out.print(" DEPT name: " + employeeDetails.get(i).getDept().getName() + ",");
				System.out.println("");
			}
		} catch (DataAccessException e) {
			System.out.println("error while retreiving a data with join " + e.getMessage());
		}
	}
	public void insertUsingBatchService(ArrayList<Employee> list) {
		try {
			int result=employeeDao.insertUsingBatch(list);
			System.out.println(result+" rows have been added...!");
		} catch (DataAccessException e) {
			System.out.println("error while using a batch commad " + e.getMessage());
		}
	}
}
