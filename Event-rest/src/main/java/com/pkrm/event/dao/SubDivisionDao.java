package com.pkrm.event.dao;

import java.util.List;

import com.pkrm.event.model.SubDivision;

public interface SubDivisionDao {
	public List<SubDivision> getAllSubDivisionNames();
	public SubDivision getMilePostAndRegion(SubDivision subDivision);
}
