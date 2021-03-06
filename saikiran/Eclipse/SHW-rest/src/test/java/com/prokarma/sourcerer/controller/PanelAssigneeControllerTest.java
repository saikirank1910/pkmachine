package com.prokarma.sourcerer.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.prokarma.sourcerer.dto.PanelAssignee;
import com.prokarma.sourcerer.service.PanelAssigneeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:application-context.xml" })
@WebAppConfiguration
public class PanelAssigneeControllerTest {
	private MockMvc mockMvc;

	@InjectMocks
	@Autowired
	private PanelAssigneeController panelAssigneeController;
	
	@Mock
	private PanelAssigneeService panelAssigneeServiceImpl;
	
	
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(this.panelAssigneeController).build();
		
	}
	@Test
	public void testsavePanel_withStatusOk() throws Exception {
		String panelJson = "{" + "\"panelId\": \"2\"," + "\"candidateId\": \"2\"," + "\"token\": \"7575\"," + "\"isVisited\": \"1\"," +"\"panelEmail\": \"xyz@gmail.com\"," + "\"modeOfInterview\": \"skype\","+ "\"round\": \"1\"" +"}";
	
		when(panelAssigneeServiceImpl.savePanelMember((PanelAssignee) any(PanelAssignee.class))).thenReturn(true);

		mockMvc.perform(post("/panelAssignee/savePanel").contentType(MediaType.APPLICATION_JSON).content(panelJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		
	}
	@Test
	public void testsavePanel_withStatusError() throws Exception {
		String panelJson = "{" + "\"panelId\": \"2\"," + "\"candidateId\": \"2\"," + "\"token\": \"7575\"," + "\"isVisited\": \"1\"," +"\"panelEmail\": \"xyz@gmail.com\"," + "\"modeOfInterview\": \"skype\","+ "\"round\": \"1\"" +"}";
	
		when(panelAssigneeServiceImpl.savePanelMember((PanelAssignee) any(PanelAssignee.class))).thenReturn(false);

		mockMvc.perform(post("/panelAssignee/savePanel").contentType(MediaType.APPLICATION_JSON).content(panelJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

		
	}
	
	@Test
	public void testAuthenticateUser_withStatusOk() throws Exception {
		
		PanelAssignee panelFromDataBase = new PanelAssignee();
		panelFromDataBase.setCandidateId(4);
		panelFromDataBase.setIsVisited(0);
		panelFromDataBase.setToken(2332);
		
		PanelAssignee panelAssignee = new PanelAssignee();
		panelAssignee.setToken(2332);
		
		when(panelAssigneeServiceImpl.verifyPanel(any(PanelAssignee.class))).thenReturn(panelFromDataBase);
		String loginPanelJson="{\"email\":\"ssss@gmail.com\",\"token\":\"2332\"}";
		MvcResult result= mockMvc.perform(post("/panelAssignee/panellogin").contentType(MediaType.APPLICATION_JSON).content(loginPanelJson)
	.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

			String responseSubtechnology=  result.getResponse().getContentAsString();
				
		
			assertEquals(
					"{\"panelId\":0,\"candidateId\":4,\"token\":2332,\"isVisited\":0,\"panelEmail\":null,\"panelName\":null,\"modeOfInterview\":null,\"round\":0}",responseSubtechnology);
	}
	
	@Test
	public void testAuthenticateUser_withStatusNoContent() throws Exception {
		
		PanelAssignee panelFromDataBase = new PanelAssignee();
		panelFromDataBase.setCandidateId(4);
		panelFromDataBase.setIsVisited(1);
		panelFromDataBase.setToken(2120);
		
		PanelAssignee panelAssignee = new PanelAssignee();
		panelAssignee.setToken(2332);
		
		when(panelAssigneeServiceImpl.verifyPanel(any(PanelAssignee.class))).thenReturn(panelFromDataBase);
		String loginPanelJson="{\"email\":\"ssss@gmail.com\",\"token\":\"2332\"}";
		MvcResult result= mockMvc.perform(post("/panelAssignee/panellogin").contentType(MediaType.APPLICATION_JSON).content(loginPanelJson)
	.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent()).andReturn();

			String responseSubtechnology=  result.getResponse().getContentAsString();
				
		
			assertEquals("",responseSubtechnology);
	}
	
}
