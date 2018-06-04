package com.pkrm.dao;

import java.util.function.Consumer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.pkrm.person.Person;

public class PersonDao {
	final static Logger logger = Logger.getLogger(Consumer.class);
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
		}else {
			logger.info("data inserted in person..!");
		}
		return i;
	}
/*
	public void edit(Person person) {
		String sql = "update person_week12 set firstname=:firstName ,lastname=:lastName where id =:id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("firstName", person.getFirstName());
		params.addValue("lastName", person.getLastName());
		params.addValue("id", person.getUserid());
		int i = namedParameterJdbcTemplate.update(sql, params);
		if (i == 0) {
			logger.info("failed to insert..!");
		}
	}

	public void delete(int id) {
		String sql = "delete from person_week12 where id =:id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		namedParameterJdbcTemplate.update(sql, params);
	}*/
}
