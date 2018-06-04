package com.pkrm.event.service;

import java.util.List;

import com.pkrm.event.model.SubDivision;

public interface SubdivisionService {
	public List<SubDivision> getAllSubDivisionNames();
	public SubDivision getMilePostAndRegion(SubDivision subDivision);
}
