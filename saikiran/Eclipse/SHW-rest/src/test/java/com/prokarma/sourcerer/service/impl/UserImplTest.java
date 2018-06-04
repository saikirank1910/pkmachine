package com.prokarma.sourcerer.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.prokarma.sourcerer.dao.UserDao;
import com.prokarma.sourcerer.dto.Email;
import com.prokarma.sourcerer.dto.User;
import com.prokarma.sourcerer.utils.GenerateMessage;
import com.prokarma.sourcerer.utils.SMTP;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:application-context.xml" })

public class UserImplTest {

	@Mock
	UserDao userdaoimpl;
	@Mock
	SMTP smtp;
	@Mock
	 GenerateMessage generateMessage;
	
	private MockMvc mockMvc;
	
	@InjectMocks
	UserServiceImpl userserviceimpl =new UserServiceImpl();
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(this.userserviceimpl).build();
	}
@Test
public void userCredentialsAreGivenToService_ReturnUserObject() throws Exception{
	User user =new User();
	user.setPassword("Sourcerer");
	User dbUser=new User();
	dbUser.setPassword("Sourcerer");
	when(userdaoimpl.getUser(user)).thenReturn(dbUser);
	assertEquals(dbUser,userserviceimpl.getUserService(user));	 
}

@Test
public void userCredentialsAreGivenToServiceFail_ReturnUserObject() throws Exception{
	User user =new User();
	user.setPassword("Sourcerer");
	User dbUser=new User();
	dbUser.setPassword("Sourcerer");
	doThrow(new NullPointerException()).when(userdaoimpl).getUser(any(User.class));
	assertEquals(null,userserviceimpl.getUserService(user));	 
}
@Test
public void updatePasswordForUser_TestPass() {
	Email email = new Email();
	email.setTo("ymounika@prokarma.com");
	email.setFrom("noreplyshw@gmail.com");
	email.setSubject("Successfully Registered for the Interview process @ProKarma");
	email.setMessage("Hello");
	when(userdaoimpl.forgotPassword((User) any(User.class))).thenReturn(insertTrueDetails());
	if(insertTrueDetails().getPassword()!=null) {
	   when(generateMessage.sendForgotPasswordToUser((User) any(User.class))).thenReturn(email);
	     assertThat(userserviceimpl.forgotPassoword(insertTrueDetails()),is(true));
	 }
}
@Test
public void updatePasswordForUser_TestFail() {
	Email email = new Email();
	email.setTo("ymounika@prokarma.com");
	email.setFrom("noreplyshw@gmail.com");
	email.setSubject("Successfully Registered for the Interview process @ProKarma");
	email.setMessage("Hello");
	 User user =new User();
	 user.setPassword(null);
	 doThrow(new NullPointerException()).when(userdaoimpl).forgotPassword(user);
	 
	assertThat(userserviceimpl.forgotPassoword(user),is(false));

}
private User insertTrueDetails() {
    User user =new User();
    user.setFirstName("Mouni");
    user.setLastName("Yellisetty");
    user.setUserName("mounikay");
    user.setPassword("ymounika");
    user.setEmail("ymounika@prokarma.com");
    return user;
}

@Test
public void userCredentialsAreGivenToInsertToService_ReturnTrue() throws Exception{

     when(userdaoimpl.insertUser(any(User.class))).thenReturn(true);
     assertThat(userserviceimpl.insertUserService(insertTrueDetails()),is(true));
}
 


@Test
public void userCredentialsAreGivenToInsertToService_ReturnFalse() throws Exception{
	doThrow(new NullPointerException()).when(userdaoimpl).insertUser((User) any(User.class));
	Boolean value=userserviceimpl.insertUserService(insertFalseDetails());
	assertEquals(false,value);
}

private User insertFalseDetails() {
    User user =new User();
    user.setFirstName("Mouni");
    user.setLastName("Yellisetty");
    user.setUserName("mounikay");
    return user;
}

List<User> listOfAllUsers = new ArrayList<User>(); 

public void getAllUserDetails() {	
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
	listOfAllUsers.add(userDetails1);
	listOfAllUsers.add(userDetails2);
}


@Test
public void getAllUserDetails_Returnlist() {
	getAllUserDetails();
	when(userdaoimpl.getAllUsers()).thenReturn(listOfAllUsers);
	List<User> listofAllUsers= userserviceimpl.getAllUsersService();
	assertEquals(listOfAllUsers, listofAllUsers);
}


@Test
public void testEditUser_pass() {
	when(userdaoimpl.editUser(any(User.class))).thenReturn(true);
	assertThat(userserviceimpl.editUserService(any(User.class)), is(true));	
}

@Test
public void testEditUser_fail() throws Exception {
	doThrow(new NullPointerException()).when(userdaoimpl).editUser((User) any(User.class));
	Boolean value=userserviceimpl.editUserService((User) any(User.class));
	assertEquals(false,value);
		
}


@Test
public void testDeleteUser_pass() {
	when(userdaoimpl.deleteUser(any(User.class))).thenReturn(true);
	assertThat(userserviceimpl.deleteUserService(any(User.class)), is(true));	
}


@Test
public void testDeleteUser_fail() throws Exception {
	doThrow(new NullPointerException()).when(userdaoimpl).deleteUser((User) any(User.class));
	Boolean value=userserviceimpl.deleteUserService((User) any(User.class));
	assertEquals(false,value);
		
}
}