package com.pkrm.event.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pkrm.event.dao.LoginDao;
import com.pkrm.event.model.Login;


@Service
public class LoginServiceImpl implements LoginService{
    
	@Autowired
	private LoginDao loginDao;
	
	public int authenticateUser(Login login) {
		
		Login userDetails=loginDao.validUser(login);
		if(userDetails.getPassWord().equals(login.getPassWord())) {
			return userDetails.getRoleId();
		}
		return 0;
	}

}
