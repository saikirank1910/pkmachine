package com.prokarma.sourcerer.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
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

import com.prokarma.sourcerer.dto.PersonalTrait;
import com.prokarma.sourcerer.service.PersonalTraitsService;

public class PersonalTraitsControllerTest {
	private MockMvc mockMvc;
	@InjectMocks
	private PersonalTraitsController personalTraitsController=new PersonalTraitsController() ;
	
	@Mock
	PersonalTraitsService  personalTraitsservice;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(this.personalTraitsController).build();
	}
	@Test
	public void testAddPersonalTrait_withStatusOk() throws Exception {
		String addPersonalTraitJson="{\"traitId\":\"3\",\"personalTrait\":\"CommunicationSkills\"}";
	
		when(personalTraitsservice.addTrait((PersonalTrait) anyObject())).thenReturn(true);
		 mockMvc.perform(post("/superadmin/addPersonalTrait").contentType(MediaType.APPLICATION_JSON).content(addPersonalTraitJson)
		    		.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	@Test
	public void testAddPersonalTrait_withStatusError() throws Exception {
		String addPersonalTraitJson="{\"traitId\":\"3\",\"personalTrait\":\"CommunicationSkills\"}";
	
		when(personalTraitsservice.addTrait((PersonalTrait) anyObject())).thenReturn(false);
		 mockMvc.perform(post("/superadmin/addPersonalTrait").contentType(MediaType.APPLICATION_JSON).content(addPersonalTraitJson)
		    		.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
	}
	@Test
	public void testEditPersonalTrait_withStatusOk() throws Exception {
		String editPersonalTraitJson="{\"traitId\":\"3\",\"personalTrait\":\"CommunicationSkills\"}";
	
		when(personalTraitsservice.editTrait((PersonalTrait) anyObject())).thenReturn(true);
		 mockMvc.perform(post("/superadmin/editPersonalTrait").contentType(MediaType.APPLICATION_JSON).content(editPersonalTraitJson)
		    		.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	@Test
	public void testEditPersonalTrait_withStatusError() throws Exception {
		String editPersonalTraitJson="{\"traitId\":\"3\",\"personalTrait\":\"CommunicationSkills\"}";
	
		when(personalTraitsservice.editTrait((PersonalTrait) anyObject())).thenReturn(false);
		 mockMvc.perform(post("/superadmin/editPersonalTrait").contentType(MediaType.APPLICATION_JSON).content(editPersonalTraitJson)
		    		.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}
	@Test
	public void testDeletePersonalTrait_withStatusOk() throws Exception {
		String deletePersonalTraitJson="{\"traitId\":\"3\",\"personalTrait\":\"CommunicationSkills\"}";
	
		when(personalTraitsservice.deleteTrait((PersonalTrait) anyObject())).thenReturn(true);
		 mockMvc.perform(post("/superadmin/deletePersonalTrait").contentType(MediaType.APPLICATION_JSON).content(deletePersonalTraitJson)
		    		.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testDeletePersonalTrait_withStatusError() throws Exception {
		String deletePersonalTraitJson="{\"traitId\":\"3\",\"personalTrait\":\"CommunicationSkills\"}";
	
		when(personalTraitsservice.deleteTrait((PersonalTrait) anyObject())).thenReturn(false);
		 mockMvc.perform(post("/superadmin/deletePersonalTrait").contentType(MediaType.APPLICATION_JSON).content(deletePersonalTraitJson)
		    		.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	
	}
	
	@Test
	public void testGetPersonalTraits() throws Exception {
		List<PersonalTrait> personalTraits= new ArrayList<PersonalTrait>();
		PersonalTrait personalTraitForConfidence= new PersonalTrait();
		personalTraitForConfidence.setPersonalTrait("Confidence");
		personalTraitForConfidence.setTraitId(1234);
		personalTraits.add(personalTraitForConfidence);
		String personalTraitsJson = "[{\"traitId\":1234,\"personalTrait\":\"Confidence\"}]";
		when(personalTraitsservice.getPersonalTraits()).thenReturn(personalTraits);
		MvcResult result=mockMvc.perform(get("/superadmin/getPersonalTraits").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
		String resultPersonalTraitsJson=result.getResponse().getContentAsString();
		assertEquals(personalTraitsJson, resultPersonalTraitsJson);
		
	}

}
