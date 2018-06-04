package com.prokarma.sourcerer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prokarma.sourcerer.dto.SkillLevel;
import com.prokarma.sourcerer.service.SkillLevelService;

@RestController
@RequestMapping("/skill")
public class SkillLevelController {

	@Autowired
	SkillLevelService skillLevelServiceImpl;

	@RequestMapping(value = "/getSkillLevel", method = RequestMethod.GET, produces = { "application/JSON" })
	public ResponseEntity<List<SkillLevel>> getTechnologies() {

		return new ResponseEntity<List<SkillLevel>>(skillLevelServiceImpl.getSkillLevels(), HttpStatus.OK);
	}

	@RequestMapping(value = "/addSkillLevel", method = RequestMethod.POST)
	public ResponseEntity<Object> addSkillLevel(@RequestBody SkillLevel skillLevel) {
		boolean result = skillLevelServiceImpl.addSkillLevel(skillLevel);
		if (result) {
			return new ResponseEntity<Object>(HttpStatus.OK);
		}
		return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/editSkillLevel", method = RequestMethod.POST)
	public ResponseEntity<Object> editSkillLevel(@RequestBody SkillLevel skillLevel) {
		boolean result = skillLevelServiceImpl.editSkillLevel(skillLevel);
		if (result) {
			return new ResponseEntity<Object>(HttpStatus.OK);
		}
		return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/deleteSkillLevel/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> deleteSkillLevel(@PathVariable int id) {
		boolean result = skillLevelServiceImpl.deleteSkillLevel(id);
		if (result) {
			return new ResponseEntity<Object>(HttpStatus.OK);
		}
		return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
	}

}
