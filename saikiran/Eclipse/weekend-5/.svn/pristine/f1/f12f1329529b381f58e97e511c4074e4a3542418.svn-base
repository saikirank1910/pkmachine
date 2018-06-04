package com.weekend.assignment5.persondetails;

import java.io.IOException;
import java.sql.SQLException;

public class PersonBO {
	private PersonDao personDao;
	public PersonDao getPersonDao() {
		return personDao;
	}
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}
	
	public boolean insertIntoPerson(Person person) throws IOException, SQLException{
		int result = getPersonDao().savePersonDetails(person);
		if(result==1)
			return true;
		return false;
	}
}
