package com.pkrm.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pkrm.event.dao.RegionDao;
import com.pkrm.event.model.Region;


@Service
public class RegionServiceImpl implements RegionService {

	@Autowired
	private RegionDao regionDao;
	
	public List<Region> getAllRegions() {
		return regionDao.getAllRegions();
	}

}
