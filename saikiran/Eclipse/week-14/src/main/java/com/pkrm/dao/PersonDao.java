package com.pkrm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.pkrm.person.Person;
import com.pkrm.person.RoleTable;

@Component
public class PersonDao {
	private Person person;
	final static Logger logger = Logger.getLogger(PersonDao.class);
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int save(Person person) {
		String sql = "insert into person_week12 values(:id,:userName,:firstName,:lastName)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", person.getUserid());
		params.addValue("userName", person.getUserName());
		params.addValue("firstName", person.getFirstName());
		params.addValue("lastName", person.getLastName());
		int i = namedParameterJdbcTemplate.update(sql, params);
		if (i == 0) {
			logger.info("failed to insert..!");
		} else {
			logger.info("data inserted in person..!");
		}
		return i;
	}

	public int edit(Person person) {
		String sql = "update person_week12 set firstname=:firstName ,lastname=:lastName ,username=:userName where id =:id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("firstName", person.getFirstName());
		params.addValue("userName", person.getUserName());
		params.addValue("lastName", person.getLastName());
		params.addValue("id", person.getUserid());
		int i = namedParameterJdbcTemplate.update(sql, params);
		if (i == 0) {
			logger.info("failed to update..!");
		}
		return i;
	}

	public void delete(int id) {
		String sql = "delete from person_week12 where id =:id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		namedParameterJdbcTemplate.update(sql, params);
	}

	public List<Person> getDetails() {
		String sql = "select p.id,p.username,p.firstname,p.lastname,r.rolename from person_week12 p,roletable_week12 r, rolemapping_week12 m where p.id=m.userid and m.roleid=r.roleid";
		 return jdbcTemplate.query(sql,new RowMapper<Person>(){
		        public Person mapRow(ResultSet rs, int row) throws SQLException {
		        	Person person = new Person();
		    		//RoleTable roleTable = new RoleTable();
		    		//person.setRole(roleTable);
		    		person.setUserid(rs.getInt(1));
//		    		roleTable.setName(rs.getString(5));
//		    		roleTable.setId(rs.getInt(6));
		    		person.setFirstName(rs.getString(3));
		    		person.setLastName(rs.getString(4));
		    		person.setUserName(rs.getString(2));
		    		person.setRoleName(rs.getString(5));
		            return person;
		        }
		    });
	}

	@Override
	public String toString() {
		return "PersonDao [person=" + person.getFirstName() + "]";
	}
}
