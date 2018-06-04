package com.prokarma.sourcerer.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.prokarma.sourcerer.dao.CandidateDao;
import com.prokarma.sourcerer.dao.PanelEvaluationDao;
import com.prokarma.sourcerer.dto.Email;
import com.prokarma.sourcerer.dto.PanelEvaluation;
import com.prokarma.sourcerer.dto.PanelSkillsRating;
import com.prokarma.sourcerer.dto.PanelTraitsRating;
import com.prokarma.sourcerer.utils.GenerateMessage;
import com.prokarma.sourcerer.utils.SMTP;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:application-context.xml" })

public class PanelServiceEvaluationImplTest {

	@Mock
	PanelEvaluationDao panelEvaluationDaoImpl;
	

	@Mock
	CandidateDao candidateDaoImpl;
	
	@Mock
	private SMTP smtp;
	@Mock
	GenerateMessage generateMessage;
	

	@InjectMocks
	PanelServiceEvaluationImpl panelServiceEvaluationImpl =new PanelServiceEvaluationImpl();
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	
	@Test
	public void testaddSkillsRating_statusOk() {
		
		when(panelEvaluationDaoImpl.addSkillsRating(any(List.class))).thenReturn(true);
		assertThat(panelServiceEvaluationImpl.addSkillsRating(any(List.class)), is(true));	

	}
	@Test
	public void testaddSkillsRating_statusError() {
		
		when(panelEvaluationDaoImpl.addSkillsRating(any(List.class))).thenReturn(false);
		assertThat(panelServiceEvaluationImpl.addSkillsRating(any(List.class)), is(false));	
	}
  
  
	@Test
	public void testaddTraitsRating_statusOk() {
	
		when(panelEvaluationDaoImpl.addTraitsRating(any(List.class))).thenReturn(true);
		assertThat(panelServiceEvaluationImpl.addTraitsRating(any(List.class)), is(true));	
	}
	
	@Test
	public void testaddTraitsRating_statusError() {
	
		when(panelEvaluationDaoImpl.addTraitsRating(any(List.class))).thenReturn(false);
		assertThat(panelServiceEvaluationImpl.addTraitsRating(any(List.class)), is(false));	
	}
	
	@Test
	public void testgetskillRating_statusOk() {
	
		List<PanelSkillsRating> listOfPanelSkillsRating=new ArrayList<PanelSkillsRating>();
		
		when(panelEvaluationDaoImpl.getskillRating(5)).thenReturn(listOfPanelSkillsRating);
		List<PanelSkillsRating> listOfGetSkillsRating = panelServiceEvaluationImpl.getskillRating(4);
		
		assertEquals(listOfPanelSkillsRating, listOfGetSkillsRating);
	}
	
	@Test
	public void testgetTraitsRating_statusOk() {
	
		List<PanelTraitsRating> listOfPanelTraitsRating=new ArrayList<PanelTraitsRating>();
		
		when(panelEvaluationDaoImpl.getTraitsRating(5)).thenReturn(listOfPanelTraitsRating);
		List<PanelTraitsRating> listOfGetTraitsRating = panelServiceEvaluationImpl.getTraitsRating(4);
		
		assertEquals(listOfPanelTraitsRating, listOfGetTraitsRating);
	}
	
	
//	@Test
//	public void testaddEvaluation_statusOk() {
//		PanelEvaluation panelEvaluation=new PanelEvaluation();
//		panelEvaluation.setStatus("select");
//		doNothing().when(candidateDaoImpl).changeCandidateStatus(any(PanelEvaluation.class));
//		when(panelEvaluationDaoImpl.changePanelStatus(any(PanelEvaluation.class))).thenReturn(true);
//		when(panelEvaluationDaoImpl.getPanelMemberAndCandidateName(any(PanelEvaluation.class))).thenReturn(panelEvaluation);
//		Email email = new Email();
//		when(generateMessage.sendMailToSuperAdminRegardingPanelFeedbackStatus(any(PanelEvaluation.class))).thenReturn(email);
//		when(smtp.sendMail(any(Email.class))).thenReturn(true);
//		when(panelEvaluationDaoImpl.addEvaluation((PanelEvaluation) any(PanelEvaluation.class))).thenReturn(true);
//		assertThat(panelServiceEvaluationImpl.addEvaluation(panelEvaluation), is(true));	
//	}
	
//	@Test
//	public void testgetEvaluationDetails_statusOk() {
//		PanelEvaluation panelEvaluation=new PanelEvaluation();
//		when(panelEvaluationDaoImpl.getEvaluationDetails(5)).thenReturn(panelEvaluation);
//		PanelEvaluation panelEvaluationObject = panelServiceEvaluationImpl.getEvaluationDetails(5);
//		
//		assertEquals(panelEvaluation, panelEvaluationObject);
//	}
//
//	@Test
//	public void testgetEvaluationDetails_statusError() throws Exception {
//	
//		doThrow(new NullPointerException()).when(panelEvaluationDaoImpl).getEvaluationDetails(5);
//		PanelEvaluation panelEvaluationObject = panelServiceEvaluationImpl.getEvaluationDetails(5);
//		assertEquals(null,panelEvaluationObject);
//		
//		
//	}
	
	
}
