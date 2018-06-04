package com.prokarma.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;


import com.prokarma.pojo.Employee;

@Component
public class EmployeeDao {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public List<Employee> getList(){
		String sql = "select *from employeetable";
		MapSqlParameterSource params = new MapSqlParameterSource();
		return namedParameterJdbcTemplate.query(sql, params, new RowMapper<Employee>() {
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee emp = new Employee();
				emp.setDesignation(rs.getString(3));
				emp.setId(rs.getInt(1));
				emp.setName(rs.getString(2));
				emp.setSalary(rs.getInt(4));
				return emp;
			}
		});
	}
	public void addEmployee(Employee employee) {
		String sql = "insert into employeetable values(empid.nextval,:name,:designation,:salary)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name",employee.getName());
		params.addValue("designation", employee.getDesignation());
		params.addValue("salary", employee.getSalary());
		int i = namedParameterJdbcTemplate.update(sql, params);
	}
	public List<Employee> getEmpById(int id) {
		String sql = "select *from employeetable where id="+id;
		MapSqlParameterSource params = new MapSqlParameterSource();
		return namedParameterJdbcTemplate.query(sql, params, new RowMapper<Employee>() {
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee emp = new Employee();
				emp.setDesignation(rs.getString(3));
				emp.setId(rs.getInt(1));
				emp.setName(rs.getString(2));
				emp.setSalary(rs.getInt(4));
				return emp;
			}
		});
	}
	public void deleteEmp(int id) {
		String sql = "delete from employeetable where id =:id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		namedParameterJdbcTemplate.update(sql, params);
	}
	public void updateEmp(Employee employee) {
		String sql = "update employeetable set name=:name ,designation=:designation ,salary=:salary where id =:id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name",employee.getName());
		params.addValue("designation",employee.getDesignation());
		params.addValue("salary",employee.getSalary());
		params.addValue("id",employee.getId());
		int i = namedParameterJdbcTemplate.update(sql, params);
	}
}
