package com.prokarma.sourcerer.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

import com.prokarma.sourcerer.dao.PanelAssigneeDao;
import com.prokarma.sourcerer.dao.PanelEvaluationDao;
import com.prokarma.sourcerer.dto.PanelAssignee;
import com.prokarma.sourcerer.dto.PanelEvaluation;
import com.prokarma.sourcerer.dto.PanelSkillsRating;
import com.prokarma.sourcerer.dto.PanelTraitsRating;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:application-context.xml" })
@Transactional

public class PanelEvaluationDaoImplTest {

	@Autowired
	private PanelEvaluationDao panelEvaluationDao;
	@Autowired
	private PanelAssigneeDao panelAssigneeDao; 
	@Autowired
	
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Test
	public void testAddEvaluation() {
		PanelEvaluation panelEvaluation = new PanelEvaluation();
		panelEvaluation.setCandidateId(4231);
		panelEvaluation.setPanelId(1);
		panelEvaluation.setComment("Good");
		panelEvaluation.setSkillLevel("asdfjkhak");
		panelEvaluation.setOverall_rating("nice");
		panelEvaluation.setStatus("waitlist");
		panelEvaluationDao.addEvaluation(panelEvaluation);
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("candidateid", 4231);
		namedParameterJdbcTemplate.queryForObject(
				"select * from panel_feedback where candidateid=:candidateid", params,
				new RowMapper<PanelEvaluation>() {
					public PanelEvaluation mapRow(ResultSet resultset, int index) throws SQLException {
						PanelEvaluation resultPanelEvaluation = new PanelEvaluation();
						resultPanelEvaluation.setCandidateId(resultset.getInt(2));
						resultPanelEvaluation.setPanelId(resultset.getInt(3));
						resultPanelEvaluation.setComment(resultset.getString(4));
						resultPanelEvaluation.setSkillLevel(resultset.getString(5));
						resultPanelEvaluation.setOverall_rating(resultset.getString(6));
						resultPanelEvaluation.setStatus(resultset.getString(7));
						return resultPanelEvaluation;
					}
				});
	}

	List<PanelSkillsRating> panelSkillsRating = new ArrayList<PanelSkillsRating>();

	private void buildPanelSkills() {
		PanelSkillsRating firstRatingObject = new PanelSkillsRating();
		firstRatingObject.setCid(4231);
		firstRatingObject.setPid(33);
		firstRatingObject.setRatingId(4);
		firstRatingObject.setSkillId(4);
		PanelSkillsRating secondRatingObject = new PanelSkillsRating();
		secondRatingObject.setCid(4232);
		secondRatingObject.setPid(34);
		secondRatingObject.setRatingId(3);
		secondRatingObject.setSkillId(5);
	}

	@Test
	public void testAddSkillsRating() {
		buildPanelSkills();
		assertEquals(true, panelEvaluationDao.addSkillsRating(panelSkillsRating));
	}

	List<PanelTraitsRating> panelTraitsRatings = new ArrayList<PanelTraitsRating>();

	private void buildPersonalSkills() {
		PanelTraitsRating firstTraitObject = new PanelTraitsRating();
		firstTraitObject.setCid(42);
		firstTraitObject.setPid(12);
		firstTraitObject.setRatingId(4);
		firstTraitObject.setSkillId(4);
		PanelTraitsRating secondTraitObject = new PanelTraitsRating();
		secondTraitObject.setCid(43);
		secondTraitObject.setPid(14);
		secondTraitObject.setRatingId(3);
		secondTraitObject.setSkillId(5);
	}

	@Test
	public void testAddTraitsRating() {
		buildPersonalSkills();
		assertEquals(true, panelEvaluationDao.addTraitsRating(panelTraitsRatings));
	}

	@Test
	public void testChangePanelStatus() {
		PanelAssignee panelAssignee = new PanelAssignee();
		panelAssignee.setPanelId(1);
		panelAssignee.setCandidateId(4231);
		panelAssignee.setToken(9999);
		panelAssignee.setPanelEmail("qwerty@gmail.com");
		panelAssignee.setModeOfInterview("face-to-face");
		panelAssigneeDao.savePanelMember(panelAssignee);
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("panel_email", "qwerty@gmail.com");
		PanelEvaluation addPanelAssignee = namedParameterJdbcTemplate.queryForObject(
				"select * from panel_assignee where panel_email=:panel_email", params, new RowMapper<PanelEvaluation>() {
					public PanelEvaluation mapRow(ResultSet resultset, int index) throws SQLException {
						PanelEvaluation resultPanelEvaluation = new PanelEvaluation();
						resultPanelEvaluation.setPanelId(resultset.getInt(2));
						resultPanelEvaluation.setCandidateId(resultset.getInt(3));
						return resultPanelEvaluation;
					}
				});
		PanelEvaluation editPanelAssignee=new PanelEvaluation();
		editPanelAssignee.setPanelId(addPanelAssignee.getPanelId());
		editPanelAssignee.setCandidateId(addPanelAssignee.getCandidateId());
		
		
		assertEquals(true, panelEvaluationDao.changePanelStatus(editPanelAssignee));
	}
	
	
	@Test
	public void getskillRating_Test() {
	panelEvaluationDao.addSkillsRating(panelSkillsRating);	
		List<PanelSkillsRating> panelSkillsRatingList=panelEvaluationDao.getskillRating(4231);
		assertNotNull(panelSkillsRatingList);
	}
	
	@Test
	public void getTraitsRating_Test() {
		panelEvaluationDao.addTraitsRating(panelTraitsRatings);	
		List<PanelTraitsRating> panelTraitsRatingList=panelEvaluationDao.getTraitsRating(4231);
		assertNotNull(panelTraitsRatingList);	
	}
	
//	@Test
//	public void getEvaluationDetails() {
//		PanelEvaluation panelEvaluation = new PanelEvaluation();
//		panelEvaluation.setCandidateId(4231);
//		panelEvaluation.setPanelId(1);
//		panelEvaluation.setComment("Good");
//		panelEvaluation.setSkillLevel("asdfjkhak");
//		panelEvaluation.setOverall_rating("nice");
//		panelEvaluation.setStatus("select");
//		panelEvaluationDao.addEvaluation(panelEvaluation);
//		PanelAssignee panelAssignee = new PanelAssignee();
//		panelAssignee.setPanelId(1);
//		panelAssignee.setCandidateId(4231);
//		panelAssignee.setToken(9999);
//		panelAssignee.setPanelEmail("qwerty@gmail.com");
//		panelAssignee.setModeOfInterview("face-to-face");
//		panelAssignee.setRound(1);
//		panelAssigneeDao.savePanelMember(panelAssignee);
//		PanelEvaluation panelEvaluationDetails=panelEvaluationDao.getEvaluationDetails(4231);
//		assertEquals("select",panelEvaluationDetails.getStatus());
//	}
	@Test
	public void getPanelMemberAndCandidateName() {
		PanelEvaluation panelEvaluation = new PanelEvaluation();
		panelEvaluation.setCandidateId(123);
		panelEvaluation.setPanelId(12);
		panelEvaluation.setComment("Good");
		panelEvaluation.setSkillLevel("asdfjkhak");
		panelEvaluation.setOverall_rating("nice");
		panelEvaluation.setStatus("select");
		panelEvaluationDao.addEvaluation(panelEvaluation);
		PanelEvaluation result = panelEvaluationDao.getPanelMemberAndCandidateName(panelEvaluation);
		assertNotNull(result);
	}
}