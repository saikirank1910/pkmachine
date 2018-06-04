package com.pkrm.event.service;

import com.pkrm.event.model.Login;

public interface LoginService {
	public int authenticateUser(Login login);
}
