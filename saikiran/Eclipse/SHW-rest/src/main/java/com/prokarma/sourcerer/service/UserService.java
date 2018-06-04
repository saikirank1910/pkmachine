package com.prokarma.sourcerer.service;

import java.util.List;

import com.prokarma.sourcerer.dto.User;

public interface UserService {
	public boolean insertUserService(User user);
	public User getUserService(User userLoginDetails);
	public List<User> getAllUsersService();
	public boolean forgotPassoword(User userDetails);
	public boolean editUserService(User userDetails);
	public boolean deleteUserService(User userDetails);
}
