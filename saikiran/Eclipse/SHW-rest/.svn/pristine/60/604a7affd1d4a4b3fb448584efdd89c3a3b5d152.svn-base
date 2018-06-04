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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.prokarma.sourcerer.dto.SkillLevel;
import com.prokarma.sourcerer.service.SkillLevelService;

public class SkillLevelControllerTest {

	private MockMvc mockMvc;
	@InjectMocks
	private SkillLevelController skillLevelController=new SkillLevelController() ;
	
	@Mock
	private SkillLevelService skillLevelService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(this.skillLevelController).build();
	}

	@Test
	public void testGetTechnoligies_withStatusOk() throws Exception {
		List<SkillLevel> skillLevels= new ArrayList<SkillLevel>();
		SkillLevel skillLevel = new SkillLevel();
		skillLevel.setSkillLevel("Hello");
		skillLevels.add(skillLevel);
		
		when(skillLevelService.getSkillLevels()).thenReturn(skillLevels);

		MvcResult result = mockMvc.perform(get("/skill/getSkillLevel")).andExpect(status().isOk()).andReturn();

		
		   String responseSkillLevels=result.getResponse().getContentAsString();
			
			assertEquals("[{\"skillId\":0,\"skillLevel\":\"Hello\"}]", responseSkillLevels);
		}
	
	
	@Test
	public void testAddSkillLevel_withStatusOk() throws Exception {
		String skillLevelJson = "{" + "\"skillId\": \"7\"," + "\"skillLevel\": \"design\"" + "}";
	
		when(skillLevelService.addSkillLevel((SkillLevel) any(SkillLevel.class))).thenReturn(true);

		mockMvc.perform(post("/skill/addSkillLevel").contentType(MediaType.APPLICATION_JSON).content(skillLevelJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		
	}
	@Test
	public void testAddSkillLevel_withStatusError() throws Exception {
		String skillLevelJson = "{" + "\"skillId\": \"7\"," + "\"skillLevel\": \"design\"" + "}";
	
		when(skillLevelService.addSkillLevel((SkillLevel) any(SkillLevel.class))).thenReturn(false);

		mockMvc.perform(post("/skill/addSkillLevel").contentType(MediaType.APPLICATION_JSON).content(skillLevelJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
		
	}
	
	@Test
	public void testEditSkillLevel_withStatusOk() throws Exception {
		String skillLevelJson = "{" + "\"skillId\": \"7\"," + "\"skillLevel\": \"design\"" + "}";
	
		when(skillLevelService.editSkillLevel((SkillLevel) any(SkillLevel.class))).thenReturn(true);

		mockMvc.perform(post("/skill/editSkillLevel").contentType(MediaType.APPLICATION_JSON).content(skillLevelJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		
	}
	
	@Test
	public void testEditSkillLevel_withStatusError() throws Exception {
		String skillLevelJson = "{" + "\"skillId\": \"7\"," + "\"skillLevel\": \"design\"" + "}";
	
		when(skillLevelService.editSkillLevel((SkillLevel) any(SkillLevel.class))).thenReturn(false);

		mockMvc.perform(post("/skill/editSkillLevel").contentType(MediaType.APPLICATION_JSON).content(skillLevelJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
		
	}
	
	@Test
	public void testDeleteSkillLevel_withStatusOk() throws Exception {
		
		
		when(skillLevelService.deleteSkillLevel(3)).thenReturn(true);
		
	    mockMvc.perform(get("/skill/deleteSkillLevel/{id}",3)).andExpect(status().isOk());
	    
	}
	
	@Test
	public void testDeleteSkillLevel_withStatusError() throws Exception {
		
	
		when(skillLevelService.deleteSkillLevel(3)).thenReturn(false);
		
	    mockMvc.perform(get("/skill/deleteSkillLevel/{id}",3)).andExpect(status().isNotFound());
	    
	}
	
}
