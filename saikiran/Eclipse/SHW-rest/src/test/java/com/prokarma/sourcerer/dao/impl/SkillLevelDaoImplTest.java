package com.prokarma.sourcerer.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.prokarma.sourcerer.dao.SkillLevelDao;
import com.prokarma.sourcerer.dto.SkillLevel;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:application-context.xml" })
@Transactional
public class SkillLevelDaoImplTest {
	
	@Autowired
	private SkillLevelDao skillLevelDaoimpl;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Test
	public void getSkillLevels() {
		SkillLevel skillLevel = new SkillLevel();
		skillLevel.setSkillLevel("DEMO");
		skillLevelDaoimpl.addSkillLevel(skillLevel);
		List<SkillLevel> list = skillLevelDaoimpl.getSkillLevels();
		assertNotNull(list);
	}
	
	@Test
	public void editSkillLevel_test() {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("skilllevel", "demo");
		namedParameterJdbcTemplate.update("insert into skill_level values(1910,:skilllevel)", params);
		SkillLevel skillLevel = new SkillLevel();
		skillLevel.setSkillId(1910);
		skillLevel.setSkillLevel("changed");
		Boolean result=skillLevelDaoimpl.editSkillLevel(skillLevel);
		assertEquals(true, result);
	}
	@Test
	public void deleteSkillLevel_test() {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("skilllevel", "demo");
		namedParameterJdbcTemplate.update("insert into skill_level values(1910,:skilllevel)", params);
		Boolean result=skillLevelDaoimpl.deleteSkillLevel(1910);
		assertEquals(true, result);
	}
}
