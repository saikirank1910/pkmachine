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

import com.prokarma.sourcerer.dto.Candidate;
import com.prokarma.sourcerer.service.CandidateService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:application-context.xml" })

public class CandidateControllerTest {
	private MockMvc mockMvc;
	@InjectMocks
	private CandidateController candidateController = new CandidateController();

	@Mock
	private CandidateService candidateService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(this.candidateController).build();
	}

	@Test
	public void getCandidatesToBeAssignedTest() throws Exception {
		List<Candidate> listOfCandidate = new ArrayList<Candidate>();
		Candidate candidate = new Candidate();
		candidate.setCid(1);
		candidate.setCname("Hello");
		candidate.setEmail("dummy@gmail.com");
		candidate.setIsVisited(0);
		candidate.setPhoneNumber(9951194899L);
		candidate.setTechnologyName("java");
		candidate.setProfile(null);
		listOfCandidate.add(candidate);
		when(candidateService.getCandidatesToBeAssigned()).thenReturn(listOfCandidate);

		MvcResult result = mockMvc.perform(get("/candidate/candidatesToBeAssigned")).andExpect(status().isOk())
				.andReturn();
		String responseCandidates = result.getResponse().getContentAsString();
		assertEquals(
				"[{\"cid\":1,\"cname\":\"Hello\",\"profile\":null,\"email\":\"dummy@gmail.com\",\"phoneNumber\":9951194899,\"technologyName\":\"java\",\"token\":0,\"isVisited\":0}]",
				responseCandidates);
	}

	@Test
	public void getCandidatesWhoAreRegisteredTest() throws Exception {
		List<Candidate> listOfCandidate = new ArrayList<Candidate>();
		Candidate candidate = new Candidate();
		candidate.setCid(1);
		candidate.setCname("Hello");
		candidate.setEmail("dummy@gmail.com");
		candidate.setIsVisited(0);
		candidate.setPhoneNumber(9951194899L);
		candidate.setTechnologyName("java");
		candidate.setProfile(null);
		listOfCandidate.add(candidate);
		when(candidateService.getCandidatesWhoAreRegistered()).thenReturn(listOfCandidate);

		MvcResult result = mockMvc.perform(get("/candidate/candidatesRegistered")).andExpect(status().isOk())
				.andReturn();
		String responseCandidates = result.getResponse().getContentAsString();
		assertEquals(
				"[{\"cid\":1,\"cname\":\"Hello\",\"profile\":null,\"email\":\"dummy@gmail.com\",\"phoneNumber\":9951194899,\"technologyName\":\"java\",\"token\":0,\"isVisited\":0}]",
				responseCandidates);
	}

	@Test
	public void getCandidatesWhoAreAssignedTest() throws Exception {
		List<Candidate> listOfCandidate = new ArrayList<Candidate>();
		Candidate candidate = new Candidate();
		candidate.setCid(1);
		candidate.setCname("Hello");
		candidate.setEmail("dummy@gmail.com");
		candidate.setIsVisited(0);
		candidate.setPhoneNumber(9951194899L);
		candidate.setTechnologyName("java");
		candidate.setProfile(null);
		listOfCandidate.add(candidate);
		when(candidateService.getCandidatesForSecondopinion()).thenReturn(listOfCandidate);

		MvcResult result = mockMvc.perform(get("/candidate/candidatesForSecondOpinion")).andExpect(status().isOk())
				.andReturn();
		String responseCandidates = result.getResponse().getContentAsString();
		assertEquals(
				"[{\"cid\":1,\"cname\":\"Hello\",\"profile\":null,\"email\":\"dummy@gmail.com\",\"phoneNumber\":9951194899,\"technologyName\":\"java\",\"token\":0,\"isVisited\":0}]",
				responseCandidates);
	}
	
	@Test	
	public void testAuthenticateUser_pass() throws Exception {
		Candidate candidateFromDb = new Candidate();
		candidateFromDb.setToken(2012);
		candidateFromDb.setIsVisited(0);
		candidateFromDb.setCid(12);
		Candidate candidate = new Candidate();
		candidate.setToken(2012);
		candidate.setIsVisited(0);
		when(candidateService.verifyCandidate(any(Candidate.class))).thenReturn(candidateFromDb);
		String loginCandidateJson="{\"email\":\"ssss@gmail.com\",\"token\":\"2012\"}";
		MvcResult result= mockMvc.perform(post("/candidate/login").contentType(MediaType.APPLICATION_JSON).content(loginCandidateJson)
	.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

			String responseSubtechnology=  result.getResponse().getContentAsString();
				
			assertEquals("12", responseSubtechnology);
	}
	
	@Test	
	public void testAuthenticateUser_fail_noContent() throws Exception {
		Candidate candidateFromDb = new Candidate();
		candidateFromDb.setToken(2212);
		candidateFromDb.setIsVisited(1);
		candidateFromDb.setCid(12);
		Candidate candidate = new Candidate();
		candidate.setToken(2012);
		candidate.setIsVisited(0);
		when(candidateService.verifyCandidate(any(Candidate.class))).thenReturn(candidateFromDb);
		String loginCandidateJson="{\"email\":\"ssss@gmail.com\",\"token\":\"2012\"}";
		MvcResult result= mockMvc.perform(post("/candidate/login").contentType(MediaType.APPLICATION_JSON).content(loginCandidateJson)
	.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()).andReturn();

			String responseSubtechnology=  result.getResponse().getContentAsString();
				
			assertEquals("", responseSubtechnology);
	}
/*	@Test
	@Ignore
	public void testgetCandidateProfile_withStatusOk() throws Exception {
		
		Candidate candidateDetails = new Candidate();
		candidateDetails.setCid(3);
		candidateDetails.setProfile(profile);
		
		when(candidateService.getCandidateDetails(3)).thenReturn(candidateDetails);

		mockMvc.perform(post("/getCandidateProfile/{cid}",3).contentType(MediaType.APPLICATION_JSON).content(skillRatingJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}*/
	@Test
	public void getCandidatesSelectedForSecondRound_withStatusOk() throws Exception
	{
		List<Candidate> listOfCandidate=new ArrayList<Candidate>();
		Candidate candidate=new Candidate();
		candidate.setCid(2);
		candidate.setCname("xyz");
		candidate.setEmail("abc@gmail.com");
		listOfCandidate.add(candidate);
		when(candidateService.getCandidatesForSecondRound()).thenReturn(listOfCandidate);
		
        MvcResult result= mockMvc.perform(get("/candidate/candidateForsecondRound")).andExpect(status().isOk()).andReturn();
	    
		String responseSubtechnology=  result.getResponse().getContentAsString();
			
		assertEquals("[{\"cid\":2,\"cname\":\"xyz\",\"profile\":null,\"email\":\"abc@gmail.com\",\"phoneNumber\":null,\"technologyName\":null,\"token\":0,\"isVisited\":0}]", responseSubtechnology);
	}
	@Test
	public void getCandidateDetails_withStatusOk() throws Exception
	{
		
		Candidate candidate=new Candidate();
		candidate.setCid(3);
		candidate.setCname("xyz");
		candidate.setEmail("abc@gmail.com");
	
		when(candidateService.getCandidateDetails(3)).thenReturn(candidate);
		
        MvcResult result= mockMvc.perform(get("/candidate/getCandidateDetails/{cid}",3)).andExpect(status().isOk()).andReturn();
	    
		String responseSubtechnology=  result.getResponse().getContentAsString();
			
		assertEquals("{\"cid\":3,\"cname\":\"xyz\",\"profile\":null,\"email\":\"abc@gmail.com\",\"phoneNumber\":null,\"technologyName\":null,\"token\":0,\"isVisited\":0}", responseSubtechnology);
	}
}
