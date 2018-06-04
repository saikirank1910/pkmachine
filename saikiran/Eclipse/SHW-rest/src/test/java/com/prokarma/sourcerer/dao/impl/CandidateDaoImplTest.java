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

import com.prokarma.sourcerer.dao.CandidateDao;
import com.prokarma.sourcerer.dto.Candidate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:application-context.xml" })
@Transactional
public class CandidateDaoImplTest {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private CandidateDao candidateDao;

	@Test
	public void getCandidatesToBeAssigned_Test() {
		Candidate candidate = new Candidate();
		candidate.setCname("saikiran");
		candidate.setEmail("skataram@prokarma.com");
		candidate.setIsVisited(1);
		candidate.setPhoneNumber(9951194899L);
		candidate.setTechnologyName("java");
		candidate.setToken(1234);
		candidateDao.save(candidate);
		List<Candidate> listOfCandidate = candidateDao.getCandidatesToBeAssigned();
		assertNotNull(listOfCandidate);
	}

	@Test
	public void getCandidatesWhoAreRegistered_test() {
		Candidate candidate = new Candidate();
 		candidate.setCname("saikiran");
		candidate.setEmail("skataram@prokarma.com");
		candidate.setIsVisited(0);
		candidate.setPhoneNumber(9951194899L);
		candidate.setTechnologyName("java");
		candidate.setToken(1234);
		candidateDao.save(candidate);
		List<Candidate> listOfCandidate = candidateDao.getCandidatesWhoAreRegistered();
		assertNotNull(listOfCandidate);
	}

	@Test
	public void getCandidatesForSecondopinion_test() {
		Candidate candidate = new Candidate();
		candidate.setCname("saikiran");
		candidate.setEmail("skataram@prokarma.com");
		candidate.setIsVisited(2);
		candidate.setPhoneNumber(9951194899L);
		candidate.setTechnologyName("java");
		candidate.setToken(1234);
		candidateDao.save(candidate);
		List<Candidate> listOfCandidate = candidateDao.getCandidatesForSecondopinion();
		assertNotNull(listOfCandidate);
	}

	@Test
	public void getCandidatesForSecondRound_test() {
		Candidate candidate = new Candidate();
		candidate.setCname("saikiran");
		candidate.setEmail("skataram@prokarma.com");
		candidate.setIsVisited(3);
		candidate.setPhoneNumber(9951194899L);
		candidate.setTechnologyName("java");
		candidate.setToken(1234);
		candidateDao.save(candidate);
		List<Candidate> listOfCandidate = candidateDao.getCandidatesForSecondRound();
		assertNotNull(listOfCandidate);
	}

	@Test
	public void getCandidateDetails_test() {
		Candidate candidate = new Candidate();
		candidate.setCid(122121);
		candidate.setCname("saikiran");
		candidate.setEmail("skataram@prokarma.com");
		candidate.setIsVisited(3);
		candidate.setPhoneNumber(9951194899L);
		candidate.setTechnologyName("java");
		candidate.setToken(1234);
		
		String query = "insert into candidate(cid,cnname,email,phonenumber,technologyname,isvisited,tokenid) values (122121,:name,:email,:number,:technologyName,:isVisited,:token)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", candidate.getCname());
		params.addValue("profile", candidate.getProfile());
		params.addValue("email", candidate.getEmail());
		params.addValue("number", candidate.getPhoneNumber());
		params.addValue("technologyName", candidate.getTechnologyName());
		params.addValue("isVisited", candidate.getIsVisited());
		params.addValue("token", candidate.getToken());
		namedParameterJdbcTemplate.update(query, params);
		Candidate candidateFromDb = candidateDao.getCandidateDetails(122121);
		assertNotNull(candidateFromDb);
	}
	@Test
	public void findTechnologyName_test() {
		Candidate candidate = new Candidate();
		candidate.setCid(122121);
		candidate.setCname("saikiran");
		candidate.setEmail("skataram@prokarma.com");
		candidate.setIsVisited(3);
		candidate.setPhoneNumber(9951194899L);
		candidate.setTechnologyName("java");
		candidate.setToken(1234);
		
		String query = "insert into candidate(cid,cnname,email,phonenumber,technologyname,isvisited,tokenid) values (122121,:name,:email,:number,:technologyName,:isVisited,:token)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", candidate.getCname());
		params.addValue("profile", candidate.getProfile());
		params.addValue("email", candidate.getEmail());
		params.addValue("number", candidate.getPhoneNumber());
		params.addValue("technologyName", candidate.getTechnologyName());
		params.addValue("isVisited", candidate.getIsVisited());
		params.addValue("token", candidate.getToken());
		namedParameterJdbcTemplate.update(query, params);
		
		Candidate candidateFromDb = candidateDao.findTechnologyName(122121);
		assertEquals("java", candidateFromDb.getTechnologyName());
	}
	
	@Test
	public void verifyCandidate_test() {
		Candidate candidate = new Candidate();
		candidate.setCid(1910);
		candidate.setCname("saikiran");
		candidate.setEmail("xdfg@prokarma.com");
		candidate.setIsVisited(3);
		candidate.setPhoneNumber(9951194899L);
		candidate.setTechnologyName("java");
		candidate.setToken(1234);
		
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
		
		Candidate candidateFromDb =candidateDao.verifyCandidate(candidate);
		assertEquals(candidate.getCid(), candidateFromDb.getCid());
		assertEquals(candidate.getToken(), candidateFromDb.getToken());
		assertEquals(candidate.getIsVisited(), candidateFromDb.getIsVisited());
	}
	@Test
	public void restrictCandidate_test() {
		Candidate candidate = new Candidate();
		candidate.setCid(1910);
		candidate.setCname("saikiran");
		candidate.setEmail("xdfg@prokarma.com");
		candidate.setIsVisited(3);
		candidate.setPhoneNumber(9951194899L);
		candidate.setTechnologyName("java");
		candidate.setToken(1234);
		Boolean result=candidateDao.restrictCandidate(1910);
		assertEquals(true, result);
	}
}
