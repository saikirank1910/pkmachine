package com.prokarma.sourcerer.service.impl;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.prokarma.sourcerer.dao.SkillLevelDao;
import com.prokarma.sourcerer.dto.SkillLevel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class SkillLevelServiceImplTest {
	
	private MockMvc mockMvc;
	@Mock
	private SkillLevelDao skillLevelDaoimpl;
	@InjectMocks
	SkillLevelServiceImpl skillLevelServiceImpl=new SkillLevelServiceImpl();
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(this.skillLevelServiceImpl).build();
	}

	@Test
	public void testGetSkillLevels() throws Exception {
		
		List<SkillLevel> skillLevels = new ArrayList<SkillLevel>();
		SkillLevel skillLevel = new SkillLevel();
		skillLevels.add(skillLevel);
		
		when(skillLevelDaoimpl.getSkillLevels()).thenReturn(skillLevels);
		List<SkillLevel> skillLevel1= skillLevelServiceImpl.getSkillLevels();
		assertEquals(skillLevels, skillLevel1);
	    	
	}
	
	@Test
	public void testAddSkillLevel() {
		SkillLevel skillLevel = new SkillLevel();
		skillLevel.setSkillId(4);
		skillLevel.setSkillLevel("design");
		when(skillLevelDaoimpl.addSkillLevel(any(SkillLevel.class))).thenReturn(true);
		assertThat(skillLevelServiceImpl.addSkillLevel(skillLevel), is(true));	
	}
	@Test
	public void testAddSkillLevelError() throws Exception {
		doThrow(new NullPointerException()).when(skillLevelDaoimpl).addSkillLevel((SkillLevel) any(SkillLevel.class));
		Boolean value=skillLevelServiceImpl.addSkillLevel((SkillLevel) any(SkillLevel.class));
		assertEquals(false,value);
		
	}
	
	@Test
	public void testEditSkillLevel() {
		SkillLevel skillLevel = new SkillLevel();
		skillLevel.setSkillId(6);
		skillLevel.setSkillLevel("design");
		when(skillLevelDaoimpl.editSkillLevel(any(SkillLevel.class))).thenReturn(true);
		assertThat(skillLevelServiceImpl.editSkillLevel(skillLevel), is(true));	
	}
	
	@Test
	public void testDeleteSkillLevel() {
			
		when(skillLevelDaoimpl.deleteSkillLevel(anyInt())).thenReturn(true);
		assertThat(skillLevelServiceImpl.deleteSkillLevel(anyInt()), is(true));	
	}

	
	
}