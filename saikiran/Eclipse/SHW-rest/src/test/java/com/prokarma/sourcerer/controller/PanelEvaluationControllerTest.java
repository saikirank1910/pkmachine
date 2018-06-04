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

import com.prokarma.sourcerer.dto.PanelEvaluation;
import com.prokarma.sourcerer.dto.PanelSkillsRating;
import com.prokarma.sourcerer.dto.PanelTraitsRating;
import com.prokarma.sourcerer.service.PanelEvaluationService;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration({"classpath:application-context.xml"})

public class PanelEvaluationControllerTest {
	
	private MockMvc mockMvc;
	@InjectMocks
	@Autowired
	private PanelEvaluationController panelEvaluationController ;
	
	@Mock
	private PanelEvaluationService  panelEvaluationService;
	
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(this.panelEvaluationController).build();
	}
	
	@Test
	public void testsubmitSkillsRating_withStatusOk() throws Exception {
		String skillRatingJson = "[{" + "\"cid\": \"2\"," + "\"pid\": \"2\"," + "\"skillId\": \"7\"," + "\"ratingId\": \"4\"" + "}]";
	
		when(panelEvaluationService.addSkillsRating(any(List.class))).thenReturn(true);

		mockMvc.perform(post("/panelEvaluation/submitSkillsRating").contentType(MediaType.APPLICATION_JSON).content(skillRatingJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted());

		
	}
	
	@Test
	public void testsubmitSkillsRating_withStatusError() throws Exception {
		String skillRatingJson = "[{"  + "\"cid\": \"2\","  + "\"pid\": \"2\"," + "\"skillId\": \"7\"," + "\"ratingId\": \"4\"" +  "}]";
	
		when(panelEvaluationService.addSkillsRating(any(List.class))).thenReturn(false);

		mockMvc.perform(post("/panelEvaluation/submitSkillsRating").contentType(MediaType.APPLICATION_JSON).content(skillRatingJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotImplemented());

		
	}

	@Test
	public void testsubmitTraitsRating_withStatusOk() throws Exception {
		String panelTraitsJson = "[{" + "\"cid\": \"2\"," + "\"pid\": \"2\"," + "\"skillId\": \"7\"," + "\"ratingId\": \"4\"" + "}]";
	
		when(panelEvaluationService.addTraitsRating(any(List.class))).thenReturn(true);

		mockMvc.perform(post("/panelEvaluation/submitTraitsRating").contentType(MediaType.APPLICATION_JSON).content(panelTraitsJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted());

		
	}
	
	@Test
	public void testsubmitTraitsRating_withStatusError() throws Exception {
		String panelTraitsJson = "[{" + "\"cid\": \"2\"," + "\"pid\": \"2\"," + "\"skillId\": \"7\"," +"\"ratingId\": \"4\"" +  "}]";
	
		when(panelEvaluationService.addTraitsRating(any(List.class))).thenReturn(false);

		mockMvc.perform(post("/panelEvaluation/submitTraitsRating").contentType(MediaType.APPLICATION_JSON).content(panelTraitsJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotImplemented());

		
	}
	
	@Test
	public void testsubmitEvaluation_withStatusOk() throws Exception {
		String panelEvaluationJson = "{" + "\"panelId\": \"2\"," + "\"candidateId\": \"2\"," + "\"comment\": \"commentary\"," +  "\"final_results\": \"finalresults\"," +  "\"overall_rating\": \"good\"," +  "\"status\": \"selected\" " +  "}";
	
		when(panelEvaluationService.addEvaluation((PanelEvaluation) any(PanelEvaluation.class))).thenReturn(true);

		mockMvc.perform(post("/panelEvaluation/submitEvaluation").contentType(MediaType.APPLICATION_JSON).content(panelEvaluationJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted());

		
	}
	
	@Test
	public void testsubmitEvaluation_withStatusError() throws Exception {
		String panelEvaluationJson = "{" + "\"panelId\": \"2\"," + "\"candidateId\": \"2\"," + "\"comment\": \"commentary\"," +  "\"final_results\": \"finalresults\"," +  "\"overall_rating\": \"good\"," +"\"status\": \"selected\" " +   "}";
	
		when(panelEvaluationService.addEvaluation((PanelEvaluation) any(PanelEvaluation.class))).thenReturn(false);

		mockMvc.perform(post("/panelEvaluation/submitEvaluation").contentType(MediaType.APPLICATION_JSON).content(panelEvaluationJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotImplemented());

		
	}
	
	@Test
	public void testgetSkillsRating_withStatusOk() throws Exception {
		
		List<PanelSkillsRating> listOfPanelSkillsRating= new ArrayList<PanelSkillsRating>();
		PanelSkillsRating panelSkillsRating=new PanelSkillsRating();
	
		panelSkillsRating.setRatingId(3);
		panelSkillsRating.setTechnologyName("java");
		
		
		listOfPanelSkillsRating.add(panelSkillsRating);
		
		when(panelEvaluationService.getskillRating(3)).thenReturn(listOfPanelSkillsRating);
		
		MvcResult result= mockMvc.perform(get("/panelEvaluation/{cid}/getSkillsRating",3)).andExpect(status().isOk()).andReturn();
	    
		 String responseSkillRating=   result.getResponse().getContentAsString();
			
		assertEquals("[{\"cid\":0,\"pid\":0,\"skillId\":0,\"ratingId\":3,\"technologyName\":\"java\"}]", responseSkillRating);

	}
	@Test
	public void testgetTraitsRating_withStatusOk() throws Exception {
		List<PanelTraitsRating> listOfPanelTraitsRating= new ArrayList<PanelTraitsRating>();
		PanelTraitsRating panelTraitsRating=new PanelTraitsRating();
		panelTraitsRating.setRatingId(4);
		panelTraitsRating.setTraitName("communicationSkills");
		
		listOfPanelTraitsRating.add(panelTraitsRating);
		
		when(panelEvaluationService.getTraitsRating(3)).thenReturn(listOfPanelTraitsRating);
		
		MvcResult result= mockMvc.perform(get("/panelEvaluation/{cid}/getTraitsRating",3)).andExpect(status().isOk()).andReturn();
	    
		String responseSkillRating=  result.getResponse().getContentAsString();
			
		assertEquals("[{\"cid\":0,\"pid\":0,\"skillId\":0,\"ratingId\":4,\"traitName\":\"communicationSkills\"}]", responseSkillRating);

	}
	@Test
	public void testgetEvaluationRemarks_withStatusOk() throws Exception {
		List<PanelEvaluation> panelEvaluationList= new ArrayList<PanelEvaluation>();
		PanelEvaluation panelEvaluation=new PanelEvaluation();
		panelEvaluation.setSkillLevel("good");
		panelEvaluation.setComment("comments");
		panelEvaluation.setOverall_rating("good");
		panelEvaluation.setStatus("select");
		panelEvaluationList.add(panelEvaluation);
		
		when(panelEvaluationService.getEvaluationDetails(3)).thenReturn(panelEvaluationList);
		
		MvcResult result=mockMvc.perform(get("/panelEvaluation/{cid}/getEvaluation",3)).andExpect(status().isOk()).andReturn();
	    
		String responseEvaluation=  result.getResponse().getContentAsString();
			
		assertEquals("[{\"panelId\":0,\"candidateId\":0,\"comment\":\"comments\",\"skillLevel\":\"good\",\"overall_rating\":\"good\",\"status\":\"select\",\"panelMemberName\":null,\"candidateName\":null}]", responseEvaluation);

	}
	
}
