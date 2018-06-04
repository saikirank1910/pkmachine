package com.prokarma.sourcerer.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prokarma.sourcerer.dto.Subtechnology;
import com.prokarma.sourcerer.dto.Technology;
import com.prokarma.sourcerer.service.TasksService;

@RestController
@RequestMapping("/superadmin")
public class TasksController {
	@Autowired
	TasksService tasksService;

	final  org.apache.log4j.Logger fileLogger = Logger.getLogger("logger.file");

	@RequestMapping(value = "/getTechnnologies", method = RequestMethod.GET, produces = { "application/JSON" })
	public ResponseEntity<List<String>> getTechnologies() {
		return new ResponseEntity<List<String>>(tasksService.getTechnologies(), HttpStatus.OK);
	}

	@RequestMapping(value = "/getSubtechnnologies", method = RequestMethod.GET, produces = { "application/JSON" })
	public ResponseEntity<List<Subtechnology>> getSubtechnologies() {
		return new ResponseEntity<List<Subtechnology>>(tasksService.getSubtechnologies(), HttpStatus.OK);
	}

	@RequestMapping(value = "/addTechnology", method = RequestMethod.POST)
	public ResponseEntity<Object> addTechnology(@RequestBody Technology technology) {
		boolean result = tasksService.addTechnology(technology);
		if (result) {
			return new ResponseEntity<Object>(HttpStatus.OK);
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/addSubtechnology", method = RequestMethod.POST)
	public ResponseEntity<Object> addSubtechnology(@RequestBody Subtechnology subTechnology) {
		boolean result = tasksService.addsubtechnology(subTechnology);
		if (result) {
			return new ResponseEntity<Object>(HttpStatus.OK);
		}
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/editSubtechnology", method = RequestMethod.POST)
	public ResponseEntity<Object> editSubtechnology(@RequestBody Subtechnology subTechnology) {
		boolean result = tasksService.editSubtechnology(subTechnology);
		if (result) {
			return new ResponseEntity<Object>(HttpStatus.OK);
		}
		return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/deleteSubtechnology/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> deleteSubtechnology(@PathVariable int id) {
		boolean result = tasksService.deleteSubtechnology(id);
		if (result) {
			return new ResponseEntity<Object>(HttpStatus.OK);
		}
		return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
	}

}