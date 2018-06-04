package com.pkrm.event.dao;

import java.util.List;

import com.pkrm.event.model.EmployeeInformation;

public interface EmployeeInformationDao {
	public int saveEmployeeEventDetails(List<EmployeeInformation> list);
}
