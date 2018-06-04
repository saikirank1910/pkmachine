package com.prokarma.sourcerer.dao;

import java.util.List;

import com.prokarma.sourcerer.dto.Subtechnology;
import com.prokarma.sourcerer.dto.Technology;

public interface TasksDao {
	public List<String> getTechnologies();

	public List<Subtechnology> getSubtechnologies();

	public boolean addTechnology(Technology technology);

	public boolean addSubtechnology(Subtechnology subTechnology);

	public boolean editSubtechnology(Subtechnology subTechnology);

	public boolean deleteSubtechnology(int id);

	public List<Subtechnology> getSubtechnologiesOfParticularSkill(String name);
}
