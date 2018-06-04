package com.prokarma.sourcerer.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prokarma.sourcerer.dao.PersonalTraitsDao;
import com.prokarma.sourcerer.dto.PersonalTrait;
import com.prokarma.sourcerer.service.PersonalTraitsService;

@Service
public class PersonalTraitsServiceImpl implements PersonalTraitsService {

	final static org.apache.log4j.Logger FileLogger = Logger.getLogger("logger.file");

	@Autowired
	PersonalTraitsDao personalTraitsDaoImpl;

	public List<PersonalTrait> getPersonalTraits() {

		return personalTraitsDaoImpl.getPersonalTraits();
	}

	public boolean addTrait(PersonalTrait personalTrait) {
		try {
			return personalTraitsDaoImpl.addPersonalTrait(personalTrait);
		} catch (Exception exception) {
			FileLogger.error("adding personal trait failed"+exception.getMessage());
			return false;
		}

	}

	public boolean editTrait(PersonalTrait personalTrait) {
		try {
			return personalTraitsDaoImpl.editPersonalTrait(personalTrait);
		} catch (Exception exception) {
			FileLogger.error("updating personal trait failed"+exception.getMessage());
			return false;
		}
	}

	public boolean deleteTrait(PersonalTrait personalTrait) {
		try {
			return personalTraitsDaoImpl.deletePersonalTrait(personalTrait);
		} catch (Exception exception) {
			FileLogger.error("deleting personal trait failed"+exception.getMessage());
			return false;
		}
	}

}
