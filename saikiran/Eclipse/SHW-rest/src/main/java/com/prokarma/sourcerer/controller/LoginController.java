package com.prokarma.sourcerer.controller;

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
@RequestMapping("/login")
public class LoginController {

	@Autowired
	UserService userserviceimpl;

	@RequestMapping(value = "/loginSuperAdmin", method = RequestMethod.POST)
	public ResponseEntity<Integer> getLoginforUser(@RequestBody User userLoginDetails)  {
		User userDetails = userserviceimpl.getUserService(userLoginDetails);
		boolean isAuthenticated = userDetails.getRoleID()==1||userDetails.getRoleID()==2;
		if(isAuthenticated)
		return new ResponseEntity<Integer>(userDetails.getRoleID(),HttpStatus.OK);

		return new ResponseEntity<Integer>(0,HttpStatus.NOT_FOUND);

	}
		
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public ResponseEntity<Object> updatePasswordforUser(@RequestBody User updateDetails)  {
		boolean result = userserviceimpl.forgotPassoword(updateDetails);
		if (result) {
			return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}
}