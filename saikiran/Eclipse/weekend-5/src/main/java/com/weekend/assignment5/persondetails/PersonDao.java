package com.weekend.assignment5.persondetails;

import java.sql.SQLException;

public interface PersonDao {
	int savePersonDetails(Person person) throws SQLException;
}
