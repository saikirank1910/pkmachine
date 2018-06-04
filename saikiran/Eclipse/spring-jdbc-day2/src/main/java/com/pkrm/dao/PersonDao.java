package com.pkrm.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;

import com.pkrm.person.Person;

public class PersonDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int save(Person person) {
		String sql = "insert into person values(:id,:firstName,:lastName)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", person.getUserid());
		params.addValue("firstName", person.getFirstName());
		params.addValue("lastName", person.getLastName());
		int i = namedParameterJdbcTemplate.update(sql, params);
		if (i == 0) {
			System.out.println("failed to insert..!");
		}
		return i;
	}

	public void edit(Person person) {
		String sql = "update person set firstname=:firstName ,lastname=:lastName where id =:id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("firstName", person.getFirstName());
		params.addValue("lastName", person.getLastName());
		params.addValue("id", person.getUserid());
		int i = namedParameterJdbcTemplate.update(sql, params);
		if (i == 0) {
			System.out.println("failed to insert..!");
		}
	}

	public void delete(int id) {
		String sql = "delete from person where id =:id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		namedParameterJdbcTemplate.update(sql, params);
	}

	public void insertUsingBatch(ArrayList<Person> list) {
		String sql = " insert into person values(:id,:firstName,:lastName)";
		List<Map<String, Object>> batchValues = new ArrayList<>(list.size());
		for (Person person : list) {
			batchValues.add(
					new MapSqlParameterSource("id", person.getUserid()).addValue("firstName", person.getFirstName())
							.addValue("lastName", person.getLastName()).getValues());
		}

		int[] updateCounts = namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[list.size()]));

	}
}
