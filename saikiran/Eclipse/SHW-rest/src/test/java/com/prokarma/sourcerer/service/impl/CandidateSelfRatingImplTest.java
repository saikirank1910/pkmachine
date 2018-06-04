package com.prokarma.sourcerer.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.prokarma.sourcerer.dao.CandidateSelfRatingDao;
import com.prokarma.sourcerer.dto.CandidateSelfRating;
import com.prokarma.sourcerer.dto.SkillLevel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class CandidateSelfRatingImplTest {
	

	@Mock
	private CandidateSelfRatingDao candidateSelfRatingDaoImpl;
	
	@InjectMocks
	CandidateSelfRatingImpl candidateSelfRatingImpl=new CandidateSelfRatingImpl();
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void testInsertSelfRating() {
		SkillLevel skillLevel = new SkillLevel();
		skillLevel.setSkillId(4);
		skillLevel.setSkillLevel("design");
		when(candidateSelfRatingDaoImpl.insertSelfRating(any(List.class))).thenReturn(true);
		assertThat(candidateSelfRatingImpl.insertSelfRating(any(List.class)), is(true));	
	}
	@Test
	public void testGetSelfRating() {
		List<CandidateSelfRating> listOfCandidateSelfRating=new ArrayList<CandidateSelfRating>();
		
		when(candidateSelfRatingDaoImpl.getSelfRating(5)).thenReturn(listOfCandidateSelfRating);
		assertEquals(candidateSelfRatingImpl.getSelfRating(5),listOfCandidateSelfRating);	
	}
	@Test
	public void testGetSelfRating_fail() {
		
		doThrow(new NullPointerException()).when(candidateSelfRatingDaoImpl).getSelfRating(4);
		assertEquals(null, candidateSelfRatingImpl.getSelfRating(4));
	}
	
}
