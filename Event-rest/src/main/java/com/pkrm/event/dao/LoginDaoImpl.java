package com.pkrm.event.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pkrm.event.model.Login;

@Repository
public class LoginDaoImpl implements LoginDao {
	private String authenticateSql="select u.username,u.password,r.role_id from user u, rolemapping r where u.username =:userName and u.user_id=r.user_id";
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public Login validUser(Login login) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userName", login.getUserName());
		
		return namedParameterJdbcTemplate.queryForObject(authenticateSql, params, new RowMapper<Login>() {
			public Login mapRow(ResultSet resultSet, int rowNum) throws SQLException {

				final Login userDetails = new Login();
				userDetails.setPassWord(resultSet.getString(2));
				userDetails.setUserName(resultSet.getString(1));
				userDetails.setRoleId(resultSet.getInt(3));
				return userDetails;
			}

		});
	}

}
