package com.prokarma.sourcerer.service;

import java.util.List;

import com.prokarma.sourcerer.dto.Subtechnology;
import com.prokarma.sourcerer.dto.Technology;

public interface TasksService {
public List<String> getTechnologies();
public List<Subtechnology> getSubtechnologies();
public boolean addTechnology(Technology technology);
public boolean addsubtechnology(Subtechnology subTechnology);
public boolean editSubtechnology(Subtechnology subtechnology);
public boolean deleteSubtechnology(int id );
}
