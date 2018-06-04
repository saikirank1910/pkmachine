package com.weekend.assignment5.persondetails;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.weekend.assignment5.persondetails.Person;
import com.weekend.assignment5.persondetails.PersonBO;
import com.weekend.assignment5.persondetails.PersonDao;


public class PersonBOTest {
	@InjectMocks
	PersonBO Bo;
	
	@Mock
	PersonDao personDao;

	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void isDataInsertedIntoPersonDB() throws IOException, SQLException{
		Person person=new Person("saikiran","MAIN-TASK-HEAD",32);
		when(personDao.savePersonDetails(person)).thenReturn(new Integer(1));
		boolean b = Bo.insertIntoPerson(person);
		Assert.assertEquals(true, b);
	}
}
