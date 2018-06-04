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

import com.prokarma.sourcerer.dao.CandidateDao;
import com.prokarma.sourcerer.dao.PanelAssigneeDao;
import com.prokarma.sourcerer.dto.Candidate;
import com.prokarma.sourcerer.dto.PanelAssignee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:application-context.xml" })



@Transactional
public class PanelAssigneeDaoImplTest {

	@Autowired
	private PanelAssigneeDao panelAssigneeDao;
	@Autowired
	private CandidateDao candidateDao;
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Test
	public void testSavePanelMember() {
		PanelAssignee panelAssignee = new PanelAssignee();
		panelAssignee.setPanelId(1);
		panelAssignee.setCandidateId(4231);
		panelAssignee.setToken(9999);
		panelAssignee.setPanelEmail("qwerty@gmail.com");
		panelAssignee.setModeOfInterview("face-to-face");
		boolean result=panelAssigneeDao.savePanelMember(panelAssignee);
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("panel_email", "qwerty@gmail.com");
		PanelAssignee addPanelAssignee = namedParameterJdbcTemplate.queryForObject(
				"select * from panel_assignee where panel_email=:panel_email", params, new RowMapper<PanelAssignee>() {
					public PanelAssignee mapRow(ResultSet resultset, int index) throws SQLException {
						PanelAssignee resultPanelAssignee = new PanelAssignee();
						resultPanelAssignee.setPanelId(resultset.getInt(2));
						resultPanelAssignee.setCandidateId(resultset.getInt(3));
						resultPanelAssignee.setToken(resultset.getInt(4));
						resultPanelAssignee.setPanelEmail(resultset.getString(6));
						resultPanelAssignee.setModeOfInterview(resultset.getString(7));
						return resultPanelAssignee;
					}
				});
		assertEquals(result,panelAssigneeDao.savePanelMember(addPanelAssignee));
	}
	
	public PanelAssignee verifyPanelDetails() {
	PanelAssignee panelAssignee = new PanelAssignee();
	panelAssignee.setPanelId(1);
	panelAssignee.setCandidateId(4236);
	panelAssignee.setToken(9999);
	panelAssignee.setPanelEmail("qwerty@prokarma.com");
	panelAssignee.setModeOfInterview("face-to-face");
	panelAssignee.setRound(0);
	panelAssignee.setIsVisited(0);
	return panelAssignee;
	}
	
	@Test
	public void verifyPanel_Test() {

		panelAssigneeDao.savePanelMember(verifyPanelDetails());
		List<PanelAssignee> listofpanelAssignee= panelAssigneeDao.verifyPanel(verifyPanelDetails());
		assertNotNull(listofpanelAssignee);
	}
	
	@Test
	public void updatePanelAssigneeStatus_TestPass() {
		Candidate candidate=new Candidate();
		candidate.setCname("Mounika");
		candidate.setEmail("mounika.smile545@gmail.com");
		candidate.setIsVisited(0);
		candidate.setPhoneNumber(8977737624L);
		candidate.setTechnologyName("java");
		candidate.setToken(9999);
		boolean resultsave=candidateDao.save(candidate);
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("cname", candidate.getCname());
		Candidate selectCandidate = namedParameterJdbcTemplate.queryForObject(
				"select * from Candidate where CNNAME=:cname", params, new RowMapper<Candidate>() {
					public Candidate mapRow(ResultSet resultset, int index) throws SQLException {
						Candidate resultCandidate=new Candidate();
						resultCandidate.setCid(resultset.getInt(1));
						return resultCandidate;
					}
				});
		System.out.println(resultsave);
		boolean result=panelAssigneeDao.updatePanelAssigneeStatus(selectCandidate.getCid(), 4);
		assertEquals(true,result);
	}

	}