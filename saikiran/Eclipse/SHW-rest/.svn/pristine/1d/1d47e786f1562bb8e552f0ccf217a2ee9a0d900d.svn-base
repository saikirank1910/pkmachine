package com.prokarma.sourcerer.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.prokarma.sourcerer.dao.UserDao;
import com.prokarma.sourcerer.dto.User;

@Repository
public class UserDaoImpl implements UserDao {

	private String insertUserSql = "insert into USERDETAILS values(userid_seq.nextVal ,:firstName,:lastName,:password,:userNameInsert,2,:emailInsert)";
	private String getUserSql = "select * from USERDETAILS where USERNAME = :username and PASSWORD = :password";
	private String forgotSql = "select password,email from USERDETAILS where USERNAME = :usernameforgot and email = :emailUser and rollid=2";
	private String editUserSql = "update USERDETAILS set FIRSTNAME=:firstname,LASTNAME=:lastname,EMAIL=:email  where USERNAME=:userName";
	private String deleteUserSql = "delete from USERDETAILS where USERNAME = :username";
	private String getAllUsersSql = "select * from USERDETAILS where ROLLID=2";

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public boolean insertUser(User user) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("firstName", user.getFirstName());
		params.addValue("lastName", user.getLastName());
		params.addValue("userNameInsert", user.getUserName());
		params.addValue("password", user.getPassword());
		params.addValue("emailInsert", user.getEmail());

		namedParameterJdbcTemplate.update(insertUserSql, params);

			return true;

	}

	public User getUser(User userLoginDetails) {

		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", userLoginDetails.getUserName());
		params.addValue("password", userLoginDetails.getPassword());
		return namedParameterJdbcTemplate.queryForObject(getUserSql, params, new RowMapper<User>() {
			public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {

				final User userDetails = new User();
				userDetails.setUserName(resultSet.getString("USERNAME"));
				userDetails.setPassword(resultSet.getString("PASSWORD"));
				userDetails.setRoleID(resultSet.getInt("ROLLID"));
				return userDetails;
			}

		});

	}

	public User forgotPassword(User userDetails) {

		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("usernameforgot", userDetails.getUserName());
		params.addValue("emailUser", userDetails.getEmail());
		return namedParameterJdbcTemplate.queryForObject(forgotSql, params, new RowMapper<User>() {
			public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				User user = new User();
				user.setPassword(resultSet.getString(1));
				user.setEmail(resultSet.getString(2));
				return user;
			}

		});
	}

	public boolean editUser(User userDetails) {

		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("firstname", userDetails.getFirstName());
		params.addValue("lastname", userDetails.getLastName());
		params.addValue("userName", userDetails.getUserName());
		params.addValue("email", userDetails.getEmail());
		namedParameterJdbcTemplate.update(editUserSql, params);

			return true;

	}

	public boolean deleteUser(User userDetails) {

		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", userDetails.getUserName());
		namedParameterJdbcTemplate.update(deleteUserSql, params);
		return true;
	}

	public List<User> getAllUsers() {
		return namedParameterJdbcTemplate.query(getAllUsersSql, new ResultSetExtractor<List<User>>() {

			public List<User> extractData(ResultSet resultSet) throws SQLException {

				List<User> list = new ArrayList<User>();
				while (resultSet.next()) {
					final User user = new User();
					user.setFirstName(resultSet.getString(2));
					user.setLastName(resultSet.getString(3));
					user.setUserName(resultSet.getString(5));
					user.setRoleID(resultSet.getInt(6));
					user.setEmail(resultSet.getString(7));
					list.add(user);
				}
				return list;
			}
		});
	}

}
