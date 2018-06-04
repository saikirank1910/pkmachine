package com.prokarma.sourcerer.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.prokarma.sourcerer.dao.UserDao;
import com.prokarma.sourcerer.dto.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:application-context.xml" })
@Transactional

public class UserDaoImplTest {
	@Autowired
	private UserDao userdao;
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public User setUserDetails() {
		User user=new User();
		user.setFirstName("Admin");
		user.setLastName("Sourcerer");
		user.setUserName("SourcererAdmin");
		user.setPassword("AdminSourcerer");
		user.setRoleID(2);
		user.setEmail("sourcererAdmin@prokarma.com");
		return user;
	}
	@Test
	public void userCredentialsAreGivenToDAO_ReturnUserDetailsFromDataBase() throws Exception {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("firstName","Admin");
		params.addValue("lastName","Sourcerer");
		params.addValue("userName", "SourcererAdmin");
		params.addValue("password", "AdminSourcerer");
		params.addValue("roleID", 2);
		params.addValue("email", "sourcererAdmin@prokarma.com");
	 namedParameterJdbcTemplate.update("insert into USERDETAILS (USERID,FIRSTNAME,LASTNAME,USERNAME,PASSWORD,ROLLID,EMAIL) values(userid_seq.nextVal,:firstName,:lastName,:userName,:password,:roleID,:email)", params);
	 User getUserDetails=userdao.getUser(setUserDetails());
	 assertEquals("SourcererAdmin",getUserDetails.getUserName());
	 assertEquals("AdminSourcerer",getUserDetails.getPassword());		
	}

	@Test
	public void updatePasswordForUser_Test() {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("firstName","Admin");
		params.addValue("lastName","Sourcerer");
		params.addValue("userName", "SourcererAdmin");
		params.addValue("password", "AdminSourcerer");
		params.addValue("roleID", 2);
		params.addValue("email", "sourcererAdmin@prokarma.com");
	 namedParameterJdbcTemplate.update("insert into USERDETAILS (USERID,FIRSTNAME,LASTNAME,USERNAME,PASSWORD,ROLLID,EMAIL) values(userid_seq.nextVal,:firstName,:lastName,:userName,:password,:roleID,:email)", params);
	 User getUserDetails=userdao.forgotPassword(setUserDetails());
	 assertEquals("AdminSourcerer",getUserDetails.getPassword());
	 assertEquals("sourcererAdmin@prokarma.com",getUserDetails.getEmail());		
	}
	
	@Test
	public void userCredentialsAreGivenToDaoToInsert_ReturnTrue() throws Exception{
	
	    userdao.insertUser(setUserDetails());
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username","SourcererAdmin");
		User addedUser = namedParameterJdbcTemplate.queryForObject(
		"select * from USERDETAILS where userName = :username ", params,
		new RowMapper<User>() {
		public User mapRow(ResultSet resultset, int index) throws SQLException {
		User resultUser = new User();
		resultUser.setFirstName(resultset.getString(2));
		resultUser.setLastName(resultset.getString(3));
		resultUser.setUserName(resultset.getString(5));
		resultUser.setPassword(resultset.getString(4));
		resultUser.setEmail(resultset.getString(7));
		return resultUser;
		}
		});
		assertEquals("SourcererAdmin",addedUser.getUserName());
		assertEquals("AdminSourcerer",addedUser.getPassword());
		}
	
	@Test
	public void getAllUsersOfRoleID2_ReturnListofUsers() {
		User userDetails1 = new User();
		userDetails1.setFirstName("Hiring1");
		userDetails1.setLastName("Wizard1");
		userDetails1.setUserName("HW1");
		userDetails1.setPassword("wizard1");
		userDetails1.setRoleID(2);
		userDetails1.setEmail("hiringWizard1@prokarma.com");
		User userDetails2 = new User();
		userDetails2.setFirstName("Hiring2");
		userDetails2.setLastName("Wizard2");
		userDetails2.setUserName("HW2");
		userDetails2.setPassword("wizard2");
		userDetails2.setRoleID(2);
		userDetails2.setEmail("hiringWizard2@prokarma.com");
		userdao.insertUser(userDetails1);
		userdao.insertUser(userDetails2);
		List<User> listOfAllUsers = userdao.getAllUsers();
		assertNotNull(listOfAllUsers);

	}
	
	
	@Test
	public void editUser_Returntrue() {

		assertEquals(true, userdao.insertUser(setUserDetails()));
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", "SourcererAdmin");

		User addedUser = namedParameterJdbcTemplate.queryForObject(
				"select * from USERDETAILS where USERNAME=:username",
				params, new RowMapper<User>() {
				
					public User mapRow(ResultSet resultset, int index) throws SQLException {
						User resultUser = new User();
						resultUser.setFirstName(resultset.getString(2));
		                resultUser.setLastName(resultset.getString(3));
		                resultUser.setUserName(resultset.getString(5));
		                resultUser.setRoleID(resultset.getInt(6));
		                resultUser.setEmail(resultset.getString(7));
						return resultUser;
					}
				});
		
		User editedUser = new User();
		editedUser.setFirstName("Hiring2");
		editedUser.setLastName(addedUser.getLastName());
		editedUser.setPassword(addedUser.getPassword());
		editedUser.setUserName(addedUser.getUserName());
		editedUser.setEmail(addedUser.getEmail());

		assertEquals(true, userdao.editUser(editedUser));
	}
	
	@Test
	public void deleteUser_Returntrue() {
		assertEquals(true, userdao.insertUser(setUserDetails()));
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", "SourcererAdmin");

		User addedUser = namedParameterJdbcTemplate.queryForObject(
				"select * from USERDETAILS where USERNAME=:username",
				params, new RowMapper<User>() {
				
					public User mapRow(ResultSet resultset, int index) throws SQLException {
						User resultUser = new User();
						resultUser.setFirstName(resultset.getString(2));
		                resultUser.setLastName(resultset.getString(3));
		                resultUser.setUserName(resultset.getString(5));
		                resultUser.setRoleID(resultset.getInt(6));
		                resultUser.setEmail(resultset.getString(7));
						return resultUser;
					}
				});
		

		assertEquals(true, userdao.deleteUser(addedUser));
	}
}
