

package com.prokarma.sourcerer.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prokarma.sourcerer.dto.User;
import com.prokarma.sourcerer.service.UserService;

@RestController
@RequestMapping("/admin")
public class UserController {
	final  org.apache.log4j.Logger fileLogger = Logger.getLogger("logger.file");
	@Autowired
	UserService userImpl;

	@RequestMapping(value = "/getAllUserDetails", method = RequestMethod.GET, produces = { "application/JSON" })

	public ResponseEntity<List<User>> getAllUsers() {

		List<User> listOfUsers = userImpl.getAllUsersService();
		return new ResponseEntity<List<User>>(listOfUsers, HttpStatus.OK);

	}

	@RequestMapping(value = "/insertuserdetails", method = RequestMethod.POST)

	public ResponseEntity<Void> getUser(@RequestBody User user) {

		boolean result = userImpl.insertUserService(user);
		if (result)
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/editUserDetails", method = RequestMethod.POST)
	public ResponseEntity<Void> editUser(@RequestBody User user) {
		boolean result = userImpl.editUserService(user);
		if (result) {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/deleteUserDetails", method = RequestMethod.POST)
	public ResponseEntity<Void> deleteUser(@RequestBody User user) {
		boolean result = userImpl.deleteUserService(user);
		if (result) {
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}