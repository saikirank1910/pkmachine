package com.pkrm.emp.usingJdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class EmployeeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Employee> getAllEmpDetails() {
		String sql = "select *from empdetails";
		List<Employee> employees = jdbcTemplate.query(sql, new RowMapper<Employee>() {
			public Employee mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
				Employee employee = new Employee();
				employee.setAge(resultSet.getInt(4));
				employee.setDeptId(resultSet.getInt(3));
				employee.setId(resultSet.getInt(1));
				employee.setName(resultSet.getString(2));
				return employee;
			}
		});
		return employees;
	}

	public int updateEmpTable(int id, String name) {
		String sql = "update empdetails set name = ? where id = ?";
		int result = jdbcTemplate.update(sql, name, id);
		return result;
	}

	public int insertIntoEmp(Employee employee) {
		int result = 0;
		String sql = "insert into empdetails values(?,?,?,?)";
		result = jdbcTemplate.update(sql, employee.getId(), employee.getName(), employee.getDeptId(),
				employee.getAge());

		return result;
	}

	public int deleteRecord(int id) {
		String sql = "delete from empdetails where id = ?";
		int result = jdbcTemplate.update(sql, id);
		return result;
	}

	public Employee getEmpById(int id) {
		String sql = "select *from empdetails where id=" + id;
		return jdbcTemplate.queryForObject(sql, new RowMapper<Employee>() {
			public Employee mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
				Employee employee = new Employee();
				employee.setAge(resultSet.getInt(4));
				employee.setDeptId(resultSet.getInt(3));
				employee.setId(resultSet.getInt(1));
				employee.setName(resultSet.getString(2));
				return employee;
			}
		});
	}

	public List<Employee> getData() {
		String sql = "select e.id,e.name,e.age,d.deptname from empdetails e,deptdetails d where e.deptid=d.id";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Employee>>() {
			public List<Employee> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
				List<Employee> list = new ArrayList<Employee>();
				while (resultSet.next()) {
					Employee e = new Employee();
					Dept d = new Dept();
					e.setDept(d);
					e.setId(resultSet.getInt(1));
					e.setName(resultSet.getString(2));
					e.setAge(resultSet.getInt(3));
					e.getDept().setName(resultSet.getString(4));
					list.add(e);
				}
				return list;
			}
		});
	}

	public int insertUsingBatch(final ArrayList<Employee> list) {
		String sql = "insert into empdetails values(?,?,?,?)";

		int[] updateCounts = jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter() {
			
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1,list.get(i).getId());
				ps.setInt(3,list.get(i).getAge());
				ps.setInt(4,list.get(i).getDeptId());
				ps.setString(2,list.get(i).getName());
				
			}
			
			public int getBatchSize() {
				return list.size();
			}
		});
		return updateCounts.length;
	}
}