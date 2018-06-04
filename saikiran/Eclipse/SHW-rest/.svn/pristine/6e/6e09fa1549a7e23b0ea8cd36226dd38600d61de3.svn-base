package com.prokarma.sourcerer.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
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

import com.prokarma.sourcerer.dao.PanelDao;
import com.prokarma.sourcerer.dto.Panel;
import com.prokarma.sourcerer.service.PanelService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml" })

public class PanelImplTest {
	@Mock
	private PanelDao panelDao;
	

	
	@InjectMocks
	@Autowired
	private PanelService panelImpl;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	List<Panel> panelList = new ArrayList<Panel>();

	private List<Panel> buildPanelMembers() {
		Panel panel1=new Panel();
		panel1.setEmail("Hello@gmail.com");
		panel1.setPanelid(1);
		panel1.setPanel_member("chaitanya");
		panel1.setTechnology("java");
		panelList.add(panel1);
		return panelList;
	}
	@Test
	public void getPanelMembersTest_true() {
		when(panelDao.getPanelMembers(anyString())).thenReturn(buildPanelMembers());
		Panel obj = new Panel();
		obj.setTechnology("java");
		List<Panel> panel = panelImpl.getPanelMembers(obj);
		assertEquals(buildPanelMembers(), panel);
	}
	
	@Test
	public void panelServiceTest_true() throws SQLException {
		Panel panel = new Panel();
		doNothing().when(panelDao).addPanel(panel);
		assertEquals(true, panelImpl.panelService(panel));
		
	}
	
	@Test
	public void panelServiceTest_false() throws SQLException {
		
		doThrow(new SQLException()).when(panelDao).addPanel(any(Panel.class));
		assertEquals(false, panelImpl.panelService(any(Panel.class)));
	}
	
	@Test
	public void getPanelDetailsTest_pass() {
		when(panelDao.getPanelDetails()).thenReturn(buildPanelMembers());
		assertEquals(buildPanelMembers(), panelImpl.getPanelDetails());
	}
	
	@Test
	public void editPanelDetailsTest_pass() {
		when(panelDao.editPanelDetails(any(Panel.class))).thenReturn(true);
		assertEquals(true, panelImpl.editPanelDetails(any(Panel.class)));
	}
	
	@Test
	public void deletePanelMemberTest_pass() {
		when(panelDao.deletePanelMember(any(Panel.class))).thenReturn(true);
		assertEquals(true, panelImpl.deletePanelMember(any(Panel.class)));
	}
	
	
}

