package com.pkrm.person.service;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pkrm.dao.PersonDao;
import com.pkrm.dao.RoleTableDao;
import com.pkrm.dao.UserRoleMapDao;
import com.pkrm.person.Person;
import com.pkrm.person.RoleTable;

public class PersonServiceTest {
	@Mock
	private RoleTableDao roleTableDao;

	@Mock
	private PersonDao personDao;

	@Mock
	private UserRoleMapDao roleMapDao;

	@InjectMocks
	private PersonService personService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreatePerson() {
		Person person = new Person();
		RoleTable roleTable = new RoleTable();
		person.setRole(roleTable);
		person.setUserid(1);
		person.setFirstName("sai kiran");
		person.setLastName("kataram");
		person.getRole().setName("developer");
		
		when(personDao.save(person)).thenReturn(new Integer(1));
		personService.createPerson(person);
		
	}

	@Test
	public void testInsertIntoRole() {
		fail("Not yet implemented");
	}

}
