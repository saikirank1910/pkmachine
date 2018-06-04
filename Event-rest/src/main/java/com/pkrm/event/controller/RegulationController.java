package com.pkrm.event.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pkrm.event.model.Regulation;
import com.pkrm.event.service.RegulationService;

@RestController
@RequestMapping(value="/regulation")
public class RegulationController {
	
	@Autowired
	private RegulationService regulationService;
	
	@RequestMapping(value="/getAllRegulations",method=RequestMethod.GET)
	public ResponseEntity<List<Regulation>> getAllRegulationDetails(){
		return new ResponseEntity<List<Regulation>>(regulationService.getAllRegulationDetails(),HttpStatus.OK);
		
	}
	
}
