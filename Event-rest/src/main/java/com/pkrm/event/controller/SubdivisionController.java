package com.pkrm.event.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pkrm.event.model.SubDivision;
import com.pkrm.event.service.SubdivisionService;

@RestController
@RequestMapping("/subDivision")
public class SubdivisionController {
	
	@Autowired
	private SubdivisionService subdivisionService;
	
	@RequestMapping(value="/getSubDivisionNames" ,method=RequestMethod.GET)
	public ResponseEntity<List<SubDivision>> getAllSubDivisionNames(){
		List<SubDivision> listOfSubDivisionNames = subdivisionService.getAllSubDivisionNames();
		return new ResponseEntity<List<SubDivision>>(listOfSubDivisionNames,HttpStatus.OK);
	}
	@RequestMapping(value="/getMilePostAndRegion" ,method=RequestMethod.POST)
	public ResponseEntity<SubDivision> getMilePostAndRegion(@RequestBody SubDivision subDivision){
		SubDivision subdivision = subdivisionService.getMilePostAndRegion(subDivision);
		return new ResponseEntity<SubDivision>(subdivision,HttpStatus.OK);
	}
}
