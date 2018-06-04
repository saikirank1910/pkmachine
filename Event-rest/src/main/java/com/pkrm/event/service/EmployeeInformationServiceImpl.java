package com.pkrm.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pkrm.event.dao.EmployeeInformationDao;
import com.pkrm.event.model.EmployeeInformation;

@Service
public class EmployeeInformationServiceImpl implements EmployeeInformationService {

	@Autowired
	private EmployeeInformationDao employeeInformationDao;

	public int saveEmployeeEventDetails(List<EmployeeInformation> list) {
		return employeeInformationDao.saveEmployeeEventDetails(list);
	}
}
