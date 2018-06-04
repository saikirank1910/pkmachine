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
import org.junit.Ignore;
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
import org.springframework.web.context.WebApplicationContext;

import com.prokarma.sourcerer.dto.CandidateSelfRating;
import com.prokarma.sourcerer.dto.Subtechnology;
import com.prokarma.sourcerer.service.CandidateSelfRatingService;
import com.prokarma.sourcerer.service.CandidateService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:application-context.xml" })
@WebAppConfiguration

public class CandidateSelfRatingControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	@Autowired
	private CandidateSelfRatingController candidateSelfRatingController;

	@Mock
	private CandidateSelfRatingService candidateSelfRatingService;

	@Mock
	private CandidateService candidateService;
	
	@Autowired
	WebApplicationContext wac;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(this.candidateSelfRatingController).build();
		
	}

	
	@Test
	@Ignore
	public void testgetTechnologiesForCandidateId_withStatusOk() throws Exception {
		
		List<Subtechnology> listOfSubtechnology= new ArrayList<Subtechnology>();
		
		Subtechnology subtechnology=new Subtechnology();
		
		subtechnology.setSubTechnology("oops");
		subtechnology.setTechnology("java");
		
		
		listOfSubtechnology.add(subtechnology);
		
		when(candidateService.returnTechnologies(5)).thenReturn(listOfSubtechnology);
		
		MvcResult result= mockMvc.perform(get("/rating/id/{cid}",5)).andExpect(status().isOk()).andReturn();
	    
		String responseSubtechnology=  result.getResponse().getContentAsString();
			
		assertEquals("[{\"technology\":\"java\",\"id\":0,\"subTechnology\":\"oops\"}]", responseSubtechnology);

	}
	
	
	@Test
	public void testgetSelfRating_withStatusOk() throws Exception {
		
		List<CandidateSelfRating> listOfCandidateSelfRating= new ArrayList<CandidateSelfRating>();
		
		CandidateSelfRating candidateSelfRating=new CandidateSelfRating();
		candidateSelfRating.setRatingId(4);
		candidateSelfRating.setTechnolgyId(3);
		candidateSelfRating.setTechnologyName("java");
		
		listOfCandidateSelfRating.add(candidateSelfRating);
		
		when(candidateSelfRatingService.getSelfRating(3)).thenReturn(listOfCandidateSelfRating);
		
		MvcResult result= mockMvc.perform(get("/rating/getSelfAssessment/{cid}",3)).andExpect(status().isOk()).andReturn();
	    
		String responseSelfRating=  result.getResponse().getContentAsString();
			
		assertEquals("[{\"cid\":0,\"technolgyId\":3,\"ratingId\":4,\"technologyName\":\"java\"}]", responseSelfRating);

	}
	
//	@Test
//	public void testGetSelfRating_withStatusError() throws Exception {
//		
//		List<CandidateSelfRating> listOfCandidateSelfRating=null;
//				
//		when(candidateSelfRatingService.getSelfRating(3)).thenReturn(listOfCandidateSelfRating);
//		
//		 mockMvc.perform(get("/rating/getSelfAssessment/{cid}",3)).andExpect(status().isOk()).andReturn();
//	}
	
	@Test
	public void submitTechnologyRatingTest_statusOk() throws Exception {
		String Json = "[{" + "\"cid\": \"28\"," + "\"technolgyId\":3," + "\"ratingId\": \"3\"" + "}]";
		List<Subtechnology> listOfSubtechnology=new ArrayList<Subtechnology>();
		Subtechnology subtechnology=new Subtechnology();
		subtechnology.setId(4);
		subtechnology.setSubTechnology("ihheritance");
		
		listOfSubtechnology.add(subtechnology);
		
		when(candidateSelfRatingService.insertSelfRating(any(List.class))).thenReturn(true);

		mockMvc.perform(post("/rating/submit").contentType(MediaType.APPLICATION_JSON).content(Json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}
	@Test
	public void submitTechnologyRatingTest_statusError() throws Exception {
		String Json = "[{" + "\"cid\": \"28\"," + "\"technolgyId\":3," + "\"ratingId\": \"3\"" + "}]";
		List<Subtechnology> listOfSubtechnology=new ArrayList<Subtechnology>();
		Subtechnology subtechnology=new Subtechnology();
		subtechnology.setId(4);
		subtechnology.setSubTechnology("ihheritance");
		
		listOfSubtechnology.add(subtechnology);
		
		when(candidateSelfRatingService.insertSelfRating(any(List.class))).thenReturn(false);

		mockMvc.perform(post("/rating/submit").contentType(MediaType.APPLICATION_JSON).content(Json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

	}
	
	
}