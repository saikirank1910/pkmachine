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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.prokarma.sourcerer.dao.PersonalTraitsDao;
import com.prokarma.sourcerer.dto.PersonalTrait;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:application-context.xml" })

public class PersonalTraitsServiceImplTest {

	@Mock
	PersonalTraitsDao personalTraitsDaoImpl;
	
	private MockMvc mockMvc;
	
	

	@InjectMocks
	PersonalTraitsServiceImpl personalTraitsServiceImpl =new PersonalTraitsServiceImpl();
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(this.personalTraitsServiceImpl).build();
	}
	@Test
	public void testAddTrait_statusOk() {
		when(personalTraitsDaoImpl.addPersonalTrait((PersonalTrait) any(PersonalTrait.class))).thenReturn(true);
		assertThat(personalTraitsServiceImpl.addTrait((PersonalTrait) any(PersonalTrait.class)), is(true));	
	}
	@Test
	public void testAddTrait_statusError() throws Exception {
		doThrow(new NullPointerException()).when(personalTraitsDaoImpl).addPersonalTrait((PersonalTrait) any(PersonalTrait.class));
		Boolean value=personalTraitsServiceImpl.addTrait((PersonalTrait) any(PersonalTrait.class));
		assertEquals(false,value);
		
	}
	
	
	@Test
	public void testEditTrait_statusOk() {
		when(personalTraitsDaoImpl.editPersonalTrait((PersonalTrait) any(PersonalTrait.class))).thenReturn(true);
		assertThat(personalTraitsServiceImpl.editTrait((PersonalTrait) any(PersonalTrait.class)), is(true));	
	}
	@Test
	public void testEditTrait_statusError() throws Exception {
		doThrow(new NullPointerException()).when(personalTraitsDaoImpl).editPersonalTrait((PersonalTrait) any(PersonalTrait.class));
		Boolean value=personalTraitsServiceImpl.editTrait((PersonalTrait) any(PersonalTrait.class));
		assertEquals(false,value);
		
	}
	@Test
	public void testDeleteTrait_statusOk() {
		when(personalTraitsDaoImpl.deletePersonalTrait((PersonalTrait) any(PersonalTrait.class))).thenReturn(true);
		assertThat(personalTraitsServiceImpl.deleteTrait((PersonalTrait) any(PersonalTrait.class)), is(true));	
	}
	@Test
	public void testDeleteTrait_statusError() throws Exception{
		doThrow(new NullPointerException()).when(personalTraitsDaoImpl).deletePersonalTrait((PersonalTrait) any(PersonalTrait.class));
		Boolean value=personalTraitsServiceImpl.deleteTrait((PersonalTrait) any(PersonalTrait.class));
		assertEquals(false,value);	
	}
	@Test
	public void testGetPersonalTraits_statusOk() {
		List<PersonalTrait> listOfPersonalTraits=new ArrayList<PersonalTrait>();
		when(personalTraitsDaoImpl.getPersonalTraits()).thenReturn(listOfPersonalTraits);
		List<PersonalTrait> personalTraitsList=personalTraitsServiceImpl.getPersonalTraits();
		assertEquals(listOfPersonalTraits,personalTraitsList);	
	}

	
}