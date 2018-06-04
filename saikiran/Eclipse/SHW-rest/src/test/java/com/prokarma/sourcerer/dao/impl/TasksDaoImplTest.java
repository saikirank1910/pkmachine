package com.prokarma.sourcerer.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.prokarma.sourcerer.dao.TasksDao;
import com.prokarma.sourcerer.dto.Subtechnology;
import com.prokarma.sourcerer.dto.Technology;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml" })
@Transactional

public class TasksDaoImplTest {
	@Autowired
	private TasksDao tasksDao;
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Test
	public void addTechnology_pass() {
		Technology technology = new Technology();
		technology.setName("Testing");

		tasksDao.addTechnology(technology);
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("technology", technology.getName());

		Technology addedTechnology = namedParameterJdbcTemplate.queryForObject(
				"select * from technicle_skills where technicleskillname = :technology", params,
				new RowMapper<Technology>() {
					public Technology mapRow(ResultSet resultset, int index) throws SQLException {
						Technology resultTechnology = new Technology();
						resultTechnology.setName(resultset.getString(2));
						return resultTechnology;
					}
				});
		assertEquals(addedTechnology.getName(), technology.getName());
	}

	@Test(expected = DuplicateKeyException.class)
	public void addTechnology_fail() {
		Technology technology = new Technology();
		technology.setName("Testing");

		tasksDao.addTechnology(technology);
		tasksDao.addTechnology(technology);
	}

	@Test
	public void addSubtechnology_pass() {
		Subtechnology subTechnology = new Subtechnology();
		subTechnology.setTechnology("java");
		subTechnology.setSubTechnology("Exception handling");

		tasksDao.addSubtechnology(subTechnology);
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("subtechnology", subTechnology.getSubTechnology());

		Subtechnology addedTechnology = namedParameterJdbcTemplate.queryForObject(
				"select m.technicleskillname,t.tsskillid,t.techniclesubskill from technicle_skills m,techinicle_sub_skills t where m.tskillid=t.tskillid and t.techniclesubskill = :subtechnology",
				params, new RowMapper<Subtechnology>() {
				
					public Subtechnology mapRow(ResultSet resultset, int index) throws SQLException {
						Subtechnology resultTechnology = new Subtechnology();
						resultTechnology.setTechnology(resultset.getString(1));
						resultTechnology.setSubTechnology(resultset.getString(3));
						resultTechnology.setId(resultset.getInt(2));
						return resultTechnology;
					}
				});
		assertEquals(addedTechnology.getTechnology(), subTechnology.getTechnology());
		assertEquals(addedTechnology.getSubTechnology(), subTechnology.getSubTechnology());
	}


	@Test(expected = DuplicateKeyException.class)
	public void addSubtechnology_fail() {
		Subtechnology subTechnology = new Subtechnology();
		subTechnology.setTechnology("java");
		subTechnology.setSubTechnology("Exception handling");

		tasksDao.addSubtechnology(subTechnology);
		tasksDao.addSubtechnology(subTechnology);
	}


	
	@Test
	public void editSubtechnology_pass() {
		Subtechnology subTechnology = new Subtechnology();
		subTechnology.setTechnology("java");
		subTechnology.setSubTechnology("Exception handling");

		assertEquals(true, tasksDao.addSubtechnology(subTechnology));
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("subtechnology", subTechnology.getSubTechnology());

		Subtechnology addedTechnology = namedParameterJdbcTemplate.queryForObject(
				"select m.technicleskillname,t.tsskillid,t.techniclesubskill from technicle_skills m,techinicle_sub_skills t where m.tskillid=t.tskillid and t.techniclesubskill = :subtechnology",
				params, new RowMapper<Subtechnology>() {
				
					public Subtechnology mapRow(ResultSet resultset, int index) throws SQLException {
						Subtechnology resultTechnology = new Subtechnology();
						resultTechnology.setTechnology(resultset.getString(1));
						resultTechnology.setSubTechnology(resultset.getString(3));
						resultTechnology.setId(resultset.getInt(2));
						return resultTechnology;
					}
				});
		
		Subtechnology editedTechnology=new Subtechnology();
		editedTechnology.setSubTechnology("Exception");
		editedTechnology.setId(addedTechnology.getId());
		editedTechnology.setTechnology(addedTechnology.getTechnology());
		
		assertEquals(true,tasksDao.editSubtechnology(editedTechnology));
		

	}
	
	@Test
	public void deleteSubtechnology_pass() {
		Subtechnology subTechnology = new Subtechnology();
		subTechnology.setTechnology("java");
		subTechnology.setSubTechnology("Exception handling");

		assertEquals(true, tasksDao.addSubtechnology(subTechnology));
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("subtechnology", subTechnology.getSubTechnology());

		Subtechnology addedTechnology = namedParameterJdbcTemplate.queryForObject(
				"select m.technicleskillname,t.tsskillid,t.techniclesubskill from technicle_skills m,techinicle_sub_skills t where m.tskillid=t.tskillid and t.techniclesubskill = :subtechnology",
				params, new RowMapper<Subtechnology>() {
					
					public Subtechnology mapRow(ResultSet resultset, int index) throws SQLException {
						Subtechnology resultTechnology = new Subtechnology();
						resultTechnology.setTechnology(resultset.getString(1));
						resultTechnology.setSubTechnology(resultset.getString(3));
						resultTechnology.setId(resultset.getInt(2));
						return resultTechnology;
					}
				});
		assertEquals(true, tasksDao.deleteSubtechnology(addedTechnology.getId()));
		
	}
	
	@Test
	public void getTechnologies_pass() {
		Technology firstTechnology = new Technology();
		firstTechnology.setName("Testing");
		Technology secondTechnology = new Technology();
		secondTechnology.setName("Oracle");
		
		tasksDao.addTechnology(firstTechnology);
		tasksDao.addTechnology(secondTechnology);
		
		List<String> technologies=tasksDao.getTechnologies();
		
	   assertNotNull(technologies);
	
	}
	
	
	@Test
	public void getSubtechnologies_pass() {
		Subtechnology firstSubTechnology = new Subtechnology();
		firstSubTechnology.setTechnology("java");
		firstSubTechnology.setSubTechnology("hngvbh");
		
		Subtechnology secondSubTechnology = new Subtechnology();
		secondSubTechnology.setTechnology("java");
		secondSubTechnology.setSubTechnology("xcgfbxcgh");
		
		tasksDao.addSubtechnology(firstSubTechnology);
		tasksDao.addSubtechnology(secondSubTechnology);
		
		List<Subtechnology> subTechnologies= tasksDao.getSubtechnologies();
		
		assertNotNull(subTechnologies);
	}
	@Test
	public void getSubtechnologiesOfParticularSkill() {
		Subtechnology firstSubTechnology = new Subtechnology();
		firstSubTechnology.setTechnology("java");
		firstSubTechnology.setSubTechnology("asd");
		
		Subtechnology secondSubTechnology = new Subtechnology();
		secondSubTechnology.setTechnology("java");
		secondSubTechnology.setSubTechnology("dfs");
		
		tasksDao.addSubtechnology(firstSubTechnology);
		tasksDao.addSubtechnology(secondSubTechnology);
		List<Subtechnology> subTechnologies= tasksDao.getSubtechnologiesOfParticularSkill("java");		
		assertNotNull(subTechnologies);
	}
	
}
