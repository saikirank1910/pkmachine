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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.prokarma.sourcerer.dao.PanelAssigneeDao;
import com.prokarma.sourcerer.dto.Email;
import com.prokarma.sourcerer.dto.PanelAssignee;
import com.prokarma.sourcerer.service.PanelAssigneeService;
import com.prokarma.sourcerer.utils.GenerateMessage;
import com.prokarma.sourcerer.utils.GenerateToken;
import com.prokarma.sourcerer.utils.SMTP;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class PanelAssigneeServiceImplTest {
	@Mock
	private PanelAssigneeDao panelAssigneeDaoImpl;

	@Mock
	private SMTP smtp;

	@Mock
	private GenerateMessage generateMessage;
	@Mock
	private GenerateToken generateToken;

	@InjectMocks
	@Autowired
	private PanelAssigneeService panelAssigneeServiceImpl;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

	}

	List<PanelAssignee> panelAssignees = new ArrayList<PanelAssignee>();

	@Test
	public void verifyPanelTest_pass() {
		buildAssigneesList();
		when(panelAssigneeDaoImpl.verifyPanel(any(PanelAssignee.class))).thenReturn(panelAssignees);
		PanelAssignee panelAssignee = new PanelAssignee();
		panelAssignee.setToken(4236);
		PanelAssignee resultAssignee = panelAssigneeServiceImpl.verifyPanel(panelAssignee);
		assertEquals(panelAssignee.getToken(), resultAssignee.getToken());

	}

	private void buildAssigneesList() {

		PanelAssignee panelAssignee = new PanelAssignee();
		panelAssignee.setToken(4236);
		panelAssignee.setCandidateId(24);
		panelAssignees.add(panelAssignee);
	}

	@Test
	public void savePanelMemberTestForRoundOne_pass() {
		PanelAssignee panelAssignee = new PanelAssignee();
		panelAssignee.setRound(1);
		when(panelAssigneeDaoImpl.savePanelMember(any(PanelAssignee.class))).thenReturn(true);
		when(smtp.sendMail(any(Email.class))).thenReturn(true);
		assertEquals(true, panelAssigneeServiceImpl.savePanelMember(panelAssignee));
	}

	@Test
	public void savePanelMemberTestForRoundTwo_pass() {
		PanelAssignee panelAssignee = new PanelAssignee();
		panelAssignee.setRound(2);
		when(panelAssigneeDaoImpl.savePanelMember(any(PanelAssignee.class))).thenReturn(true);
		when(smtp.sendMail(any(Email.class))).thenReturn(true);
		assertEquals(true, panelAssigneeServiceImpl.savePanelMember(panelAssignee));
	}

	@Test
	public void savePanelMemberTest_fail() {
		PanelAssignee panelAssignee = new PanelAssignee();
		doThrow(new NullPointerException()).when(panelAssigneeDaoImpl).savePanelMember(panelAssignee);
		when(smtp.sendMail(any(Email.class))).thenReturn(true);
		assertEquals(false, panelAssigneeServiceImpl.savePanelMember(panelAssignee));
	}

}
