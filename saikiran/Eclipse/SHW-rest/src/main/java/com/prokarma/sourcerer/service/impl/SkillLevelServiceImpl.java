package com.prokarma.sourcerer.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prokarma.sourcerer.dao.SkillLevelDao;
import com.prokarma.sourcerer.dto.SkillLevel;
import com.prokarma.sourcerer.service.SkillLevelService;

@Component
public class SkillLevelServiceImpl implements SkillLevelService {
	
	final static org.apache.log4j.Logger FileLogger = Logger.getLogger("logger.file");

	@Autowired
	SkillLevelDao skillLevelDaoimpl;
	
	public List<SkillLevel> getSkillLevels() {	
		return skillLevelDaoimpl.getSkillLevels() ;
	}
	public boolean addSkillLevel(SkillLevel skillLevel) {
		try {
		return skillLevelDaoimpl.addSkillLevel(skillLevel);
		}
		catch(Exception exception) {
			FileLogger.error("error while savinf addskilllevel"+exception.getMessage());
			return false;
		}
	}


	public boolean editSkillLevel(SkillLevel skillLevel) {
		return	skillLevelDaoimpl.editSkillLevel(skillLevel);
	}


	public boolean deleteSkillLevel(int id) {
		return skillLevelDaoimpl.deleteSkillLevel(id);
	}

	

}
