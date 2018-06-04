package com.prokarma.sourcerer.dao;

import java.util.List;

import com.prokarma.sourcerer.dto.User;

public interface UserDao {
	public boolean insertUser(User user);
	
	public List<User> getAllUsers();

	public User getUser(User userLoginDetails);
	
	public User forgotPassword(User userDetails);
	
	public boolean editUser(User userDetails);
	
	public boolean deleteUser(User userDetails);
}