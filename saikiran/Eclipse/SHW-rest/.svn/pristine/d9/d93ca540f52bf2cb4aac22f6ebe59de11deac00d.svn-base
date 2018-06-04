package com.prokarma.sourcerer.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prokarma.sourcerer.dao.TasksDao;
import com.prokarma.sourcerer.dto.Subtechnology;
import com.prokarma.sourcerer.dto.Technology;
import com.prokarma.sourcerer.service.TasksService;

@Service
public class TaksServiceImpl implements TasksService {

	final static org.apache.log4j.Logger FileLogger = Logger.getLogger("logger.file");
	@Autowired
	TasksDao tasksdaoimpl;

	public List<String> getTechnologies() {
		return tasksdaoimpl.getTechnologies();

	}

	public List<Subtechnology> getSubtechnologies() {
		return tasksdaoimpl.getSubtechnologies();
	}

	public boolean addTechnology(Technology technology) {
		try {
			return tasksdaoimpl.addTechnology(technology);
		} catch (Exception exception) {
			FileLogger.error("error while adding a technology" + exception.getMessage());
			return false;
		}
	}

	public boolean addsubtechnology(Subtechnology subTechnology) {
		try {
			return tasksdaoimpl.addSubtechnology(subTechnology);
		} catch (Exception exception) {
			FileLogger.error("error while adding a sub-technology" + exception.getMessage());
			return false;
		}
	}

	public boolean editSubtechnology(Subtechnology subtechnology) {
		return tasksdaoimpl.editSubtechnology(subtechnology);
	}

	public boolean deleteSubtechnology(int id) {
		return tasksdaoimpl.deleteSubtechnology(id);
	}

}
