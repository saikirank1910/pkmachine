package com.pkrm.dao;

import java.util.function.Consumer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.pkrm.person.Person;
import com.pkrm.person.UserRoleMap;

@Component
public class UserRoleMapDao {
	final static Logger logger = Logger.getLogger(Consumer.class);
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public int insert(UserRoleMap roleMap) {
		String sql = "insert into rolemapping_week12 values(:roleid,:userid)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("roleid", roleMap.getRoleId());
		params.addValue("userid", roleMap.getUserId());

		int i = namedParameterJdbcTemplate.update(sql, params);
		if (i == 0) {
			logger.info("failed to insert..!");
		} else {
			logger.info("data inserted in rolemap table..!");
		}

		return i;
	}

	public void delete(int id) {
		String sql = "delete from rolemapping_week12 where userid =:id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		namedParameterJdbcTemplate.update(sql, params);
	}
	
	public int edit(UserRoleMap roleMap) {
		String sql = "update rolemapping_week12 set roleid=:roleId where userId =:id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("roleId", roleMap.getRoleId());
		params.addValue("userId", roleMap.getUserId());
		
		int i = namedParameterJdbcTemplate.update(sql, params);
		if (i == 0) {
			System.out.println("failed to insert..!");
		}
		return i;
	}
}
