package com.pkrm.event.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pkrm.event.model.Employee;


@Repository
public class EmployeeDaoImpl implements EmployeeDao{
	private String getAllEmployeesSql="SELECT employee_Id,employee_name FROM employee";
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public List<Employee> getAllEmployees() {
		return namedParameterJdbcTemplate.query(getAllEmployeesSql, new RowMapper<Employee>() {
			public Employee mapRow(ResultSet resultSet, int index) throws SQLException {
				Employee employee = new Employee();
				employee.setEmployeeId(resultSet.getInt(1));
				employee.setEmployeeName(resultSet.getString(2));
				return employee;
			}

		});
	}

}
