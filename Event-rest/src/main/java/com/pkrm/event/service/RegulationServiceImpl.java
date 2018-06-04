package com.pkrm.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pkrm.event.dao.RegulationDao;
import com.pkrm.event.model.Regulation;

@Service
public class RegulationServiceImpl implements RegulationService {

	@Autowired
	private RegulationDao regulationDao;
	
	public List<Regulation> getAllRegulationDetails() {
		return regulationDao.getAllRegulationDetails();
	}

}
