package com.pkrm.event.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.pkrm.event.config.ContextConfiguration;
import com.pkrm.event.model.Login;


@RunWith(SpringJUnit4ClassRunner.class)
@org.springframework.test.context.ContextConfiguration(classes= {ContextConfiguration.class})
@WebAppConfiguration
@Transactional
@Rollback(true)
public class LoginDaoImplTest {
	
	@Autowired
	private LoginDao loginDao;

	@Test
	public void testValidUser() {
		Login login = new Login();
		login.setUserName("admin");
		
		Login loginDetailsFromDb = loginDao.validUser(login);
		assertEquals("admin@123", loginDetailsFromDb.getPassWord());
		assertEquals(1, loginDetailsFromDb.getRoleId());
	}

}
