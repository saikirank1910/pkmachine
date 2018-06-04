package com.pkrm.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pkrm.event.dao.ServiceUnitDao;
import com.pkrm.event.model.ServiceUnit;


@Service
public class ServiceUnitServiceImpl implements ServiceUnitService{

	@Autowired
	private ServiceUnitDao serviceUnitDao;
	
	public List<ServiceUnit> getAllServiceUnits() {
		return serviceUnitDao.getAllServiceUnits();
	}

}
