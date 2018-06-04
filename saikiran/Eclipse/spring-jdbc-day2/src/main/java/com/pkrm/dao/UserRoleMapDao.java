package com.pkrm.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.pkrm.person.UserRoleMap;

public class UserRoleMapDao {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	public int insert(UserRoleMap roleMap) {
		String sql = "insert into rolemapping values(:roleid,:userid)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("roleid",roleMap.getRoleId());
		params.addValue("userid", roleMap.getUserId());
		int i = namedParameterJdbcTemplate.update(sql,params);
		if (i == 0) {
			System.out.println("failed to insert..!");
		}
		return i;
	}
	public void delete(int id) {
		String sql = "delete from rolemapping where userid =:id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id",id);
		namedParameterJdbcTemplate.update(sql,params);
	}
}
