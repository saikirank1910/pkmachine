package com.pkrm.event.service;

import java.util.List;

import com.pkrm.event.model.EmployeeInformation;

public interface EmployeeInformationService {
	public int saveEmployeeEventDetails(List<EmployeeInformation> list);
}
