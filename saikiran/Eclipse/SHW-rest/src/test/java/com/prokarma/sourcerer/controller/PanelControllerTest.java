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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.prokarma.sourcerer.dto.Panel;
import com.prokarma.sourcerer.service.PanelService;
@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration({"classpath:application-context.xml"})

public class PanelControllerTest {

	private MockMvc mockMvc;
	@InjectMocks
	@Autowired
	private PanelController panelController ;
	
	@Mock
	PanelService panelServiceImpl;
	

	
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(this.panelController).build();
	}
	
	@Test
	public void testaddPanel_withStatusOk() throws Exception {
		String addPanelJson = "{" + "\"panelid\": \"2\"," + "\"panel_member\": \"abcd\"," + "\"email\": \"abc@gmail.com\"," + "\"technology\": \"java\"" + "}";
		
		when(panelServiceImpl.panelService((Panel)any(Panel.class))).thenReturn(true);

		mockMvc.perform(post("/panel/addPanel").contentType(MediaType.APPLICATION_JSON).content(addPanelJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted());	
	}
	@Test
	public void testaddPanel_withStatusError() throws Exception {
		String addPanelJson = "{" + "\"panelid\": \"2\"," + "\"panel_member\": \"abcd\"," + "\"email\": \"abc@gmail.com\"," + "\"technology\": \"java\"" + "}";
		
		when(panelServiceImpl.panelService((Panel)any(Panel.class))).thenReturn(false);

		mockMvc.perform(post("/panel/addPanel").contentType(MediaType.APPLICATION_JSON).content(addPanelJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotAcceptable());	
	}
	

	@Test
	public void testgetPanelMembers_withStatusOk() throws Exception {
		
		String addPanelJson = "{" + "\"panelid\": \"2\"," + "\"panel_member\": \"abcd\"," + "\"email\": \"abc@gmail.com\"," + "\"technology\": \"java\"" + "}";
		
		List<Panel> listOfPanelMembers= new ArrayList<Panel>();
		
		Panel panel=new Panel();
		panel.setPanelid(3);
		panel.setPanel_member("xyz");
		panel.setEmail("abc@gmail.com");
		panel.setTechnology("java");
		
		listOfPanelMembers.add(panel);
		
		when(panelServiceImpl.getPanelMembers((Panel)any(Panel.class))).thenReturn(listOfPanelMembers);
		
	    
		MvcResult result=mockMvc.perform(post("/panel/getPanel").contentType(MediaType.APPLICATION_JSON).content(addPanelJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
		
		String responseSkillRating=  result.getResponse().getContentAsString();
			
		assertEquals("[{\"panelid\":3,\"panel_member\":\"xyz\",\"email\":\"abc@gmail.com\",\"technology\":\"java\"}]", responseSkillRating);

	}  
	@Test
	public void testgetPanelMembers_withStatusError() throws Exception {
		
		String addPanelJson = "{" + "\"panelid\": \"2\"," + "\"panel_member\": \"abcd\"," + "\"email\": \"abc@gmail.com\"," + "\"technology\": \"java\"" + "}";
		
		List<Panel> listOfPanelMembers= new ArrayList<Panel>();
		
		Panel panel=new Panel();
		panel.setPanelid(3);
		panel.setPanel_member("xyz");
		panel.setEmail("abc@gmail.com");
		panel.setTechnology("java");
		
		
		
		when(panelServiceImpl.getPanelMembers((Panel)any(Panel.class))).thenReturn(listOfPanelMembers);
		
		
		MvcResult result=mockMvc.perform(post("/panel/getPanel").contentType(MediaType.APPLICATION_JSON).content(addPanelJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent()).andReturn();
		
		String responseSkillRating=  result.getResponse().getContentAsString();
			
		assertEquals("[]", responseSkillRating);

	}

	@Test
	public void testgetAllUsers_withStatusOk() throws Exception {

		String getPanelUsersJson = "{" + "\"panelid\": \"2\"," + "\"panel_member\": \"abcd\"," + "\"email\": \"abc@gmail.com\"," + "\"technology\": \"java\"" + "}";
		
		List<Panel> listOfPanelMembers= new ArrayList<Panel>();
		
		Panel panel=new Panel();
		panel.setPanelid(3);
		panel.setPanel_member("xyz");
		panel.setEmail("abc@gmail.com");
		panel.setTechnology("java");
		
		listOfPanelMembers.add(panel);
		
		when(panelServiceImpl.getPanelDetails()).thenReturn(listOfPanelMembers);

		MvcResult result=mockMvc.perform(get("/panel/getAllPanelDetails").contentType(MediaType.APPLICATION_JSON).content(getPanelUsersJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
		
		String responsePanelDetails=  result.getResponse().getContentAsString();
		
		assertEquals("[{\"panelid\":3,\"panel_member\":\"xyz\",\"email\":\"abc@gmail.com\",\"technology\":\"java\"}]",responsePanelDetails);
		
	}
	
	@Test
	public void testEditUser_withStatusOk() throws Exception {
		
			String editUserJson = "{" + "\"panelid\": \"2\"," + "\"panel_member\": \"abc\"," + "\"email\": \"abc@gmail.com\"," + "\"technology\": \"java\"" + "}";
		
			when(panelServiceImpl.editPanelDetails((Panel) any(Panel.class))).thenReturn(true);

			mockMvc.perform(post("/panel/editPanelDetails").contentType(MediaType.APPLICATION_JSON).content(editUserJson)
					.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}
	

	@Test
	public void testEditUser_withStatusError() throws Exception {
		
			String editUserJson = "{" + "\"panelid\": \"2\"," + "\"panel_member\": \"abc\"," + "\"email\": \"abc@gmail.com\"," + "\"technology\": \"java\"" + "}";
		
			when(panelServiceImpl.editPanelDetails((Panel) any(Panel.class))).thenReturn(false);

			mockMvc.perform(post("/panel/editPanelDetails").contentType(MediaType.APPLICATION_JSON).content(editUserJson)
					.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
	}
	
	
	@Test
	public void testDeleteUser_withStatusOk() throws Exception {
		
			String deleteUserJson = "{" + "\"panelid\": \"2\"," + "\"panel_member\": \"abc\"," + "\"email\": \"abc@gmail.com\"," + "\"technology\": \"java\"" + "}";
		
			when(panelServiceImpl.deletePanelMember((Panel) any(Panel.class))).thenReturn(true);

			mockMvc.perform(post("/panel/deletePanelDetails").contentType(MediaType.APPLICATION_JSON).content(deleteUserJson)
					.accept(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted());
	}
	
	@Test
	public void testDeleteUser_withStatusError() throws Exception {
		
			String deleteUserJson = "{" + "\"panelid\": \"2\"," + "\"panel_member\": \"abc\"," + "\"email\": \"abc@gmail.com\"," + "\"technology\": \"java\"" + "}";
		
			when(panelServiceImpl.deletePanelMember((Panel) any(Panel.class))).thenReturn(false);

			mockMvc.perform(post("/panel/deletePanelDetails").contentType(MediaType.APPLICATION_JSON).content(deleteUserJson)
					.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}
	
	
	
}
