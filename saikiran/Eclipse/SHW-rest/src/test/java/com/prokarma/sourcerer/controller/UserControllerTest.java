package com.prokarma.sourcerer.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.prokarma.sourcerer.dto.User;
import com.prokarma.sourcerer.service.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:application-context.xml" })
public class UserControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private UserController userController=new UserController();
	
	@Mock
	private UserService userService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(this.userController).build();
	}
	
	public User setUserDetails() {
		User user=new User();
		user.setFirstName("Admin");
		user.setLastName("Sourcerer");
		user.setUserName("SourcererAdmin");
		user.setPassword("AdminSourcerer");
		user.setRoleID(2);
		user.setEmail("sourcererAdmin@prokarma.com");
		return user;
	}
	@Test
	public void getAllUsers_Test() throws Exception {
	List<User> listOfUsers= new ArrayList<User>();
	listOfUsers.add(setUserDetails());
	when(userService.getAllUsersService()).thenReturn(listOfUsers);

	MvcResult result = mockMvc.perform(get("/admin/getAllUserDetails")).andExpect(status().isOk()).andReturn();
	
	   String responseUser=result.getResponse().getContentAsString();
		
		assertEquals("[{\"firstName\":\"Admin\",\"lastName\":\"Sourcerer\",\"userName\":\"SourcererAdmin\",\"password\":\"AdminSourcerer\",\"roleID\":2,\"email\":\"sourcererAdmin@prokarma.com\"}]", responseUser);
	}
	@Test
	public void insertUser_Test() throws Exception {
		String loginFormJson = "{" + "\"firstName\": \"Mouni\"," + "\"lastName\": \"Yellisetty\"," + "\"userName\": \"mounikay\"," + "\"password\": \"ymounika\"," + "\"email\": \"ymounika@prokarma.com\""+ "}";
		when(userService.insertUserService((User) any(User.class))).thenReturn(true);

		mockMvc.perform(post("/admin/insertuserdetails").contentType(MediaType.APPLICATION_JSON).content(loginFormJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

	}
	@Test
	public void insertUser_TestError() throws Exception {
		String loginFormJson = "{" + "\"firstName\": \"Mouni\"," + "\"lastName\": \"Yellisetty\"," + "\"userName\": \"mounikay\"," + "\"password\": \"ymounika\"," + "\"email\": \"ymounika@prokarma.com\""+ "}";
		when(userService.insertUserService((User) any(User.class))).thenReturn(false);

		mockMvc.perform(post("/admin/insertuserdetails").contentType(MediaType.APPLICATION_JSON).content(loginFormJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

	}
	@Test
	public void editUser_Test() throws Exception{
		String editUserFormJson ="{" + "\"firstName\": \"Sorcerer\"," + "\"lastName\": \"Admin\"," + "\"userName\": \"sourcererAdmin\"," + "\"password\": \"adminSourcerer\","+ "\"email\": \"sourcereradmin@prokarma.com\""+ "}";
		setUserDetails();
		when(userService.editUserService((User) any(User.class))).thenReturn(true);

		mockMvc.perform(post("/admin/editUserDetails").contentType(MediaType.APPLICATION_JSON).content(editUserFormJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

	}
	@Test
	public void editUser_TestError() throws Exception{
		String editUserFormJson ="{" + "\"firstName\": \"Sorcerer\"," + "\"lastName\": \"Admin\"," + "\"userName\": \"sourcererAdmin\"," + "\"password\": \"adminSourcerer\","+ "\"email\": \"sourcereradmin@prokarma.com\""+ "}";
		setUserDetails();
		when(userService.editUserService((User) any(User.class))).thenReturn(false);

		mockMvc.perform(post("/admin/editUserDetails").contentType(MediaType.APPLICATION_JSON).content(editUserFormJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

	}
	@Test
	public void deleteUser_Test()  throws Exception{
		String deleteUserFormJson="{"+"\"userName\": \"sourcererAdmin\"" +"}";
		when(userService.deleteUserService((User) any(User.class))).thenReturn(true);

		mockMvc.perform(post("/admin/deleteUserDetails").contentType(MediaType.APPLICATION_JSON).content(deleteUserFormJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted());

		
	}
	@Test
	public void deleteUser_TestError()  throws Exception{
		String deleteUserFormJson="{"+"\"userName\": \"sourcererAdmin\"" +"}";
		when(userService.deleteUserService((User) any(User.class))).thenReturn(false);

		mockMvc.perform(post("/admin/deleteUserDetails").contentType(MediaType.APPLICATION_JSON).content(deleteUserFormJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

		
	}
}
