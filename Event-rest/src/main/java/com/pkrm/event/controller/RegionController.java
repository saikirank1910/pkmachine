package com.pkrm.event.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pkrm.event.model.Region;
import com.pkrm.event.service.RegionService;

@RestController
@RequestMapping(value="/region")
public class RegionController {

	@Autowired
	private RegionService regionService;
	

	@RequestMapping(value="/getAllRegions" ,method=RequestMethod.GET)
	public ResponseEntity<List<Region>> getAllRegions(){
		return new ResponseEntity<List<Region>>(regionService.getAllRegions(),HttpStatus.OK);	
	}
}
