package com.prokarma.sourcerer.dao.impl;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.prokarma.sourcerer.dto.Candidate;
import com.prokarma.sourcerer.dto.CandidateSelfRating;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:application-context.xml" })
@Transactional
public class CandidateSelfRatingDaoImplTest {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private CandidateSelfRatingDaoImpl candidateSelfRatingDaoImpl;
	
	@Test
	public void getSelfRating_test() {
		Candidate candidate = new Candidate();
		candidate.setCid(1910);
		candidate.setCname("saikiran");
		candidate.setEmail("skataram@prokarma.com");
		candidate.setIsVisited(3);
		candidate.setPhoneNumber(9951194899L);
		candidate.setTechnologyName("java");
		candidate.setToken(1910);
		
		String query = "insert into candidate(cid,cnname,email,phonenumber,technologyname,isvisited,tokenid) values (1910,:name,:email,:number,:technologyName,:isVisited,:token)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", candidate.getCname());
		params.addValue("profile", candidate.getProfile());
		params.addValue("email", candidate.getEmail());
		params.addValue("number", candidate.getPhoneNumber());
		params.addValue("technologyName", candidate.getTechnologyName());
		params.addValue("isVisited", candidate.getIsVisited());
		params.addValue("token", candidate.getToken());
		namedParameterJdbcTemplate.update(query, params);
		
		
		List<CandidateSelfRating> listOfSelfRating=new ArrayList<CandidateSelfRating>();
		CandidateSelfRating candidateSelfRating = new CandidateSelfRating();
		candidateSelfRating.setCid(1910);
		candidateSelfRating.setRatingId(1);
		candidateSelfRating.setTechnolgyId(12);
		listOfSelfRating.add(candidateSelfRating);
		
		candidateSelfRatingDaoImpl.insertSelfRating(listOfSelfRating);
		List<CandidateSelfRating> listFromDb=candidateSelfRatingDaoImpl.getSelfRating(1910);
		assertNotNull(listFromDb);
	}
}