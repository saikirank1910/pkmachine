package com.pkrm.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pkrm.event.dao.SubDivisionDao;
import com.pkrm.event.model.SubDivision;

@Service
public class SubDivisionServiceImpl implements SubdivisionService{

	
	@Autowired
	private SubDivisionDao subDivisionDao;
	
	
	public List<SubDivision> getAllSubDivisionNames() {
		List<SubDivision> listOfSubDivisionNames = subDivisionDao.getAllSubDivisionNames();
		return listOfSubDivisionNames;
	}


	public SubDivision getMilePostAndRegion(SubDivision subDivision) {
		SubDivision subdivision = subDivisionDao.getMilePostAndRegion(subDivision);
		return subdivision;
	}

}
