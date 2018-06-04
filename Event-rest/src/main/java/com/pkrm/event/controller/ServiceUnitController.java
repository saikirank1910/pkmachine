package com.pkrm.event.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pkrm.event.model.ServiceUnit;
import com.pkrm.event.service.ServiceUnitService;

@RestController
@RequestMapping(value="/serviceUnit")
public class ServiceUnitController {

	
	@Autowired
	private ServiceUnitService serviceUnitService;
	
	@RequestMapping(value="/getAllServiceUnits" ,method=RequestMethod.GET)
	public ResponseEntity<List<ServiceUnit>> getAllServiceUnits(){
		return new ResponseEntity<List<ServiceUnit>>(serviceUnitService.getAllServiceUnits(),HttpStatus.OK);	
	}
}
