package com.pkrm.event.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pkrm.event.model.Event;
import com.pkrm.event.service.EventService;

@RestController
@RequestMapping("/event")
public class EventController {

	
	@Autowired
	private EventService eventService;
	
	@RequestMapping(value="/save" ,method=RequestMethod.POST)
	public ResponseEntity<Integer> insertAddress(@RequestBody Event event) {
		System.out.println(event);
	int result=eventService.saveEvent(event);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
}
