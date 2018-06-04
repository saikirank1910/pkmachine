package com.pkrm.event.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pkrm.event.model.Login;
import com.pkrm.event.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping(value="/authenticate" ,method=RequestMethod.POST)
	public ResponseEntity<Integer> insertAddress(@RequestBody Login login) {
		int result = loginService.authenticateUser(login);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
}
