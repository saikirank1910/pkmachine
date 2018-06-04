package com.prokarma.sourcerer.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prokarma.sourcerer.dao.UserDao;
import com.prokarma.sourcerer.dto.Email;
import com.prokarma.sourcerer.dto.User;
import com.prokarma.sourcerer.service.UserService;
import com.prokarma.sourcerer.utils.GenerateMessage;
import com.prokarma.sourcerer.utils.SMTP;

@Service
public class UserServiceImpl implements UserService {
	
	final static org.apache.log4j.Logger FileLogger = Logger.getLogger("logger.file");
	
	@Autowired
	UserDao userdaoimpl;

	@Autowired
	private SMTP smtp;

	@Autowired
	private GenerateMessage generateMessage;

	public User getUserService(User userLoginDetails) {
		try {
		User userDetails = userdaoimpl.getUser(userLoginDetails);

			boolean isUserExists = userDetails.getPassword().equals(userLoginDetails.getPassword());
			if (isUserExists) {
				return userDetails;
			}
		} catch (Exception exception) {
			FileLogger.error("error while retreiving data"+exception.getMessage());
		}
		return null;
	}

	public boolean forgotPassoword(User userDetails) {
		boolean success = false;
		try {
			User userFromDb = userdaoimpl.forgotPassword(userDetails);
			if (userFromDb.getPassword() != null) {
				Email toUser = generateMessage.sendForgotPasswordToUser(userFromDb);
				smtp.sendMail(toUser);
				success=true;
			}
		} catch (Exception exception) {
			FileLogger.error("error in forgot password service"+exception.getMessage());
			return success;
		}
		return success;
	}

	public boolean insertUserService(User user) {
		try {
			GenerateMessage generateMessage = new GenerateMessage();
			Email generateMailToAdmin = generateMessage.sendEmailToAdminRegardingResgistration(user);
			smtp.sendMail(generateMailToAdmin);
		return userdaoimpl.insertUser(user);
		}catch(Exception exception) {
			FileLogger.error("error while saving a user"+exception.getMessage());
			return false;
		}

	}

	public boolean editUserService(User userDetails) {
		try {
			return userdaoimpl.editUser(userDetails);
		} catch (final Exception exception) {
			FileLogger.error("error in edit User service"+exception.getMessage());
			return false;
		}
	}

	public boolean deleteUserService(User userDetails) {
		try {
			return userdaoimpl.deleteUser(userDetails);
		} catch (final Exception exception) {
			FileLogger.error("error in delete user service"+exception.getMessage());
			return false;
		}
	}

	public List<User> getAllUsersService() {
		return userdaoimpl.getAllUsers();
	}

}
