package com.prokarma.sourcerer.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.prokarma.sourcerer.dto.User;
import com.prokarma.sourcerer.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:application-context.xml" })

public class LoginControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private LoginController loginController = new LoginController();

	@Mock
	private UserService userService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(this.loginController).build();
	}

	@Test
	public void whenUserCredetialsAreValid_RespondWithStatusOkforRoleid1() throws Exception {
		String loginFormJson = "{" + "\"userName\": \"mounika\"," + "\"password\": \"yellisetty\"" + "}";

		User user = new User();
		user.setFirstName("firstName");
		user.setLastName("lastName");
		user.setRoleID(1);

		when(userService.getUserService((User) any(User.class))).thenReturn(user);

		mockMvc.perform(post("/login/loginSuperAdmin").contentType(MediaType.APPLICATION_JSON).content(loginFormJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$", is(1))).andExpect(status().isOk());

	}


	@Test
	public void whenUserCredetialsAreValid_RespondWithStatusOkforRoleid2() throws Exception {
		String loginFormJson = "{" + "\"userName\": \"mounika\"," + "\"password\": \"yellisetty\"" + "}";

		User user = new User();
		user.setFirstName("firstName");
		user.setLastName("lastName");
		user.setRoleID(2);

		when(userService.getUserService((User) any(User.class))).thenReturn(user);

		mockMvc.perform(post("/login/loginSuperAdmin").contentType(MediaType.APPLICATION_JSON).content(loginFormJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$", is(2))).andExpect(status().isOk());

	}

	@Test
	public void whenUserCredetialsAreValid_RespondWithStatusError() throws Exception {
		String loginFormJson = "{" + "\"userName\": \"mounika\"," + "\"password\": \"yellisetty\"" + "}";

		User user = new User();
		user.setFirstName("firstName");
		user.setLastName("lastName");
		user.setRoleID(0);

		when(userService.getUserService((User) any(User.class))).thenReturn(user);

		mockMvc.perform(post("/login/loginSuperAdmin").contentType(MediaType.APPLICATION_JSON).content(loginFormJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$", is(0))).andExpect(status().isNotFound());

	}
	@Test
	public void updatePasswordforUser_TestWithStatusBadRequest() throws Exception{
		String loginFormJson = "{" + "\"userName\": \"mounika\"," + "\"email\": \"ymounika@prokarma.com\"" + "}";

		when(userService.forgotPassoword((User) any(User.class))).thenReturn(false);

		mockMvc.perform(post("/login/forgotpassword").contentType(MediaType.APPLICATION_JSON).content(loginFormJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

	}
	@Test
	public void updatePasswordforUser_TestWithStatusAccepted() throws Exception{
		String loginFormJson = "{" + "\"userName\": \"mounika\"," + "\"email\": \"ymounika@prokarma.com\"" + "}";

		when(userService.forgotPassoword((User) any(User.class))).thenReturn(true);

		mockMvc.perform(post("/login/forgotpassword").contentType(MediaType.APPLICATION_JSON).content(loginFormJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted());

	}
}