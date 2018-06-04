package com.prokarma.sourcerer.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
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

import com.prokarma.sourcerer.dao.CandidateDao;
import com.prokarma.sourcerer.dao.TasksDao;
import com.prokarma.sourcerer.dto.Candidate;
import com.prokarma.sourcerer.dto.Email;
import com.prokarma.sourcerer.dto.Subtechnology;
import com.prokarma.sourcerer.service.CandidateService;
import com.prokarma.sourcerer.utils.GenerateMessage;
import com.prokarma.sourcerer.utils.GenerateToken;
import com.prokarma.sourcerer.utils.SMTP;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml" })

public class CandidateServiceTest {
	
	@InjectMocks
	CandidateService candidateService = new CandidateServiceImpl();

	@Mock
	private GenerateToken generateToken;
	
	@Mock
	private CandidateDao candidateDao;
	
	@Mock
	private TasksDao tasksDao;
	
	@Mock
	private GenerateMessage generateMessage;
	
	@Mock
	private SMTP smtp;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	List<Candidate> candidate = new ArrayList<Candidate>();

	private List<Candidate> buildCandidates() {
		Candidate candidate1 = new Candidate();
		candidate1.setCid(1);
		candidate1.setCname("Sai");
		candidate1.setEmail("hello@gmail.com");

		Candidate candidate2 = new Candidate();
		candidate2.setCid(1);
		candidate2.setCname("Sai");
		candidate2.setEmail("dummy@gmail.com");
		candidate.add(candidate1);
		candidate.add(candidate2);
		return candidate;
	}
	
	
	@Test
	public void getCandidatesToBeAssigned_true() {
		when(candidateDao.getCandidatesToBeAssigned()).thenReturn(buildCandidates());
		List<Candidate> candidate = candidateService.getCandidatesToBeAssigned();
		assertEquals(buildCandidates(), candidate);
	}

	@Test
	public void getCandidatesWhoAreRegistered_true() {
		when(candidateDao.getCandidatesWhoAreRegistered()).thenReturn(buildCandidates());
		List<Candidate> candidate = candidateService.getCandidatesWhoAreRegistered();
		assertEquals(buildCandidates(), candidate);
	}
	
	@Test
	public void getCandidatesWhoAreAssignedToPanel_true() {
		when(candidateDao.getCandidatesForSecondopinion()).thenReturn(buildCandidates());
		List<Candidate> candidate = candidateService.getCandidatesForSecondopinion();
		assertEquals(buildCandidates(), candidate);
	}
	
	@Test
	public void getCandidateDetails_true() {
		Candidate candidate=new Candidate();
		when(candidateDao.getCandidateDetails(4)).thenReturn(candidate);
		Candidate candidateObject = candidateService.getCandidateDetails(4);
		assertEquals(candidate, candidateObject);
	}
	
	@Test
	public void getCandidatesForSecondRound_true() {
		when(candidateDao.getCandidatesForSecondRound()).thenReturn(buildCandidates());
		List<Candidate> candidate = candidateService.getCandidatesForSecondRound();
		assertEquals(buildCandidates(), candidate);
	}
	
	@Test
	public void restrictCandidate_true() {
		
		when(candidateDao.restrictCandidate(4)).thenReturn(true);
		Boolean candidateObject = candidateService.restrictCandidate(4);
		assertEquals(true, candidateObject);
	}
	
	@Test
	public void verifyCandidate_true() {
		
		Candidate candidate=new Candidate();
		when(candidateDao.verifyCandidate((Candidate) any(Candidate.class))).thenReturn(candidate);
		Candidate candidateFromDb = candidateService.verifyCandidate(candidate);
		assertEquals(candidate, candidateFromDb);
	}
	
	@Test
	public void returnTechnologies_true() {
		List<Subtechnology> listOfSubTechnology=new ArrayList<Subtechnology>();
		Candidate candidate=new Candidate();
		candidate.setCname("xyz");
		candidate.setEmail("abc@gmail.com");
		candidate.setTechnologyName("java");
		when(candidateDao.findTechnologyName(4)).thenReturn(candidate);
		when(tasksDao.getSubtechnologiesOfParticularSkill(candidate.getTechnologyName())).thenReturn(listOfSubTechnology);
		List<Subtechnology> subTechnologyList = candidateService.returnTechnologies(4);
		assertEquals(listOfSubTechnology, subTechnologyList);
	}
	
	@Test
	public void saveCandidate_true() {
		Candidate candidate=new Candidate();
		Email email=new Email();
		email.setFrom("abc");
		email.setTo("xyz");		
		
		when(candidateDao.save((Candidate) any(Candidate.class))).thenReturn(true);
		when(generateMessage.sendEmailToCandidate((Candidate) any(Candidate.class))).thenReturn(email);
		when(smtp.sendMail(any(Email.class))).thenReturn(true);
	    Boolean value=candidateService.saveCandidate(candidate);
		assertEquals(true, value);
	}
	
	@Test
	public void saveCandidate_fail() {
		Candidate candidate=new Candidate();
		Email email=new Email();
		email.setFrom("abc");
		email.setTo("xyz");		
		doThrow(new NullPointerException()).when(candidateDao).save((Candidate) any(Candidate.class));
		when(generateMessage.sendEmailToCandidate((Candidate) any(Candidate.class))).thenReturn(email);
		when(smtp.sendMail(any(Email.class))).thenReturn(true);
	    Boolean value=candidateService.saveCandidate(candidate);
		assertEquals(false, value);	
	}
}