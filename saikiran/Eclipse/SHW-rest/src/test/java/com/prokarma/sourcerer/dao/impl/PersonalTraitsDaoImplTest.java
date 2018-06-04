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

import com.prokarma.sourcerer.dto.PersonalTrait;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:application-context.xml" })
@Transactional
public class PersonalTraitsDaoImplTest {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private PersonalTraitsDaoImpl personalTraitsDaoImpl;

	@Test
	public void getPersonalTraits_test() {
		PersonalTrait personalTrait = new PersonalTrait();
		personalTrait.setPersonalTrait("HELLO");
		personalTraitsDaoImpl.addPersonalTrait(personalTrait);
		List<PersonalTrait> list = personalTraitsDaoImpl.getPersonalTraits();
		assertNotNull(list);
	}

	@Test
	public void editPersonalTrait_test() {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("trait_name","demo");
		namedParameterJdbcTemplate.update("insert into PERSONAL_TRAITS values(1234,:trait_name)",params);
		
		PersonalTrait personalTrait = new PersonalTrait();
		personalTrait.setPersonalTrait("HELLO");
		personalTrait.setTraitId(1234);
		Boolean result=personalTraitsDaoImpl.editPersonalTrait(personalTrait);
		assertEquals(true, result);

	}

	@Test
	public void deletePersonalTrait_test() {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("trait_name","demo");
		namedParameterJdbcTemplate.update("insert into PERSONAL_TRAITS values(1234,:trait_name)",params);
		
		PersonalTrait personalTrait = new PersonalTrait();
		personalTrait.setPersonalTrait("HELLO");
		personalTrait.setTraitId(1234);
		Boolean result=personalTraitsDaoImpl.deletePersonalTrait(personalTrait);
		assertEquals(true, result);
	}

}
