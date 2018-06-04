package com.prokarma.sourcerer.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.prokarma.sourcerer.dao.PanelDao;
import com.prokarma.sourcerer.dto.Panel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:application-context.xml" })
@Transactional

public class PanelDaoImplTest {
	@Autowired
	private PanelDao panelDao;
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Test
	public void whenPanelMemberAreGivenToDaoToInsert_ReturnTrue() throws SQLException {
		Panel panel = new Panel();
		panel.setPanel_member("Saikiran");
		panel.setEmail("qwert@gail.com");
		panel.setTechnology("java");
		panelDao.addPanel(panel);
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("panel_member", "Saikiran");
		Panel addPanelMember = namedParameterJdbcTemplate
				.queryForObject("select * from PANEL where panel_member=:panel_member", params, new RowMapper<Panel>() {
					public Panel mapRow(ResultSet resultset, int index) throws SQLException {
						Panel resultPanel = new Panel();
						resultPanel.setPanel_member(resultset.getString(2));
						resultPanel.setEmail(resultset.getString(3));
						resultPanel.setTechnology(resultset.getString(4));
						return resultPanel;
					}
				});
		assertEquals(panel.getPanel_member(), addPanelMember.getPanel_member());
	}

	@Test
	public void getPanelMembersBySkill_ReturnListofPanelMemebers() throws SQLException {
		Panel panel = new Panel();
		panel.setPanel_member("Saikiran");
		panel.setEmail("qwert@gmail.com");
		panel.setTechnology("java");
		panelDao.addPanel(panel);
		Panel testPanel=new Panel();
		testPanel.setPanel_member("Akhil");
		testPanel.setEmail("qwert@f.com");
		testPanel.setTechnology("java");
		panelDao.addPanel(testPanel);
		List<Panel> listOfAllPanelMembers=panelDao.getPanelMembers("java");
		assertNotNull(listOfAllPanelMembers);
	}
	
	@Test
	public void getAllPanelDetails_ReturnListOfDetails() throws SQLException {
		Panel testPanel=new Panel();
		testPanel.setPanel_member("Akhil");
		testPanel.setEmail("qwer@gmail.com");
		testPanel.setTechnology("java");
		panelDao.addPanel(setPanelDetails());
		panelDao.addPanel(testPanel);
		List<Panel> listOfAllPanelDetails = panelDao.getPanelDetails();
		assertNotNull(listOfAllPanelDetails);

	}
	public Panel setPanelDetails() {
		Panel panel = new Panel();
		panel.setPanel_member("Saikiran");
		panel.setEmail("qwert@gmail.com");
		panel.setTechnology("java");
		return panel;
	}
	@Test
	public void editPanelDetails_Test() throws SQLException {
		panelDao.addPanel(setPanelDetails());
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("panel_member", "Saikiran");
		Panel addedPanel = namedParameterJdbcTemplate.queryForObject(
				"select * from PANEL where panel_member=:panel_member",
				params, new RowMapper<Panel>() {
				
					public Panel mapRow(ResultSet resultset, int index) throws SQLException {
						Panel resultPanel = new Panel();
						resultPanel.setPanelid(resultset.getInt(1));
						resultPanel.setPanel_member(resultset.getString(2));
						resultPanel.setEmail(resultset.getString(3));
						resultPanel.setTechnology(resultset.getString(4));
						return resultPanel;
					}
				});
		
		Panel testPanel=new Panel();
		testPanel.setPanel_member("Chaitanya");
		testPanel.setEmail(addedPanel.getEmail());
		testPanel.setTechnology(addedPanel.getTechnology());
		testPanel.setPanelid(addedPanel.getPanelid());
		assertEquals(true, panelDao.editPanelDetails(testPanel));
	}
	

	@Test
	public void deletePanelMember_Test() throws SQLException {
		panelDao.addPanel(setPanelDetails());
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("panel_member", "Saikiran");
		Panel addedPanel = namedParameterJdbcTemplate.queryForObject(
				"select * from PANEL where panel_member=:panel_member",
				params, new RowMapper<Panel>() {
				
					public Panel mapRow(ResultSet resultset, int index) throws SQLException {
						Panel resultPanel = new Panel();
						resultPanel.setPanelid(resultset.getInt(1));
						resultPanel.setPanel_member(resultset.getString(2));
						resultPanel.setEmail(resultset.getString(3));
						resultPanel.setTechnology(resultset.getString(4));
						return resultPanel;
					}
				});
		
		assertEquals(true, panelDao.deletePanelMember(addedPanel));
	}

}
