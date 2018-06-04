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

import com.prokarma.sourcerer.dto.PanelEvaluation;
import com.prokarma.sourcerer.dto.PanelSkillsRating;
import com.prokarma.sourcerer.dto.PanelTraitsRating;
import com.prokarma.sourcerer.service.PanelEvaluationService;

@RestController
@RequestMapping("/panelEvaluation")
public class PanelEvaluationController {
	
	@Autowired
	private PanelEvaluationService panelEvaluationService;
	
	@RequestMapping(value = "/submitEvaluation", method = RequestMethod.POST)
	public ResponseEntity<Void> addEvaluationRemarks(@RequestBody PanelEvaluation panelEvaluation) {
		Boolean result=panelEvaluationService.addEvaluation(panelEvaluation);
		if(result)
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
		return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
	}
	
	@RequestMapping(value = "/submitSkillsRating", method = RequestMethod.POST)
	public ResponseEntity<Void> addSkillsRating(@RequestBody List<PanelSkillsRating> list) {
		Boolean result=panelEvaluationService.addSkillsRating(list);
		if(result)
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
		return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
	}
	
	@RequestMapping(value = "/submitTraitsRating", method = RequestMethod.POST)
	public ResponseEntity<Void> addTraitsRating(@RequestBody List<PanelTraitsRating> list) {
		Boolean result=panelEvaluationService.addTraitsRating(list);
		if(result)
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
		return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
	}
	
	@RequestMapping(value = "{cid}/getEvaluation", method = RequestMethod.GET)
	public ResponseEntity<List<PanelEvaluation>> getEvaluationRemarks(@PathVariable("cid") int candidateId) {
		List<PanelEvaluation> panelEvaluation = panelEvaluationService.getEvaluationDetails(candidateId);
		return new ResponseEntity<List<PanelEvaluation>>(panelEvaluation,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{cid}/getSkillsRating", method = RequestMethod.GET)
	public ResponseEntity<List<PanelSkillsRating>> getSkillsRating(@PathVariable("cid") int candidateId) {
		List<PanelSkillsRating> listOfSkillsRating = panelEvaluationService.getskillRating(candidateId);
		return new ResponseEntity<List<PanelSkillsRating>>(listOfSkillsRating,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{cid}/getTraitsRating", method = RequestMethod.GET)
	public ResponseEntity<List<PanelTraitsRating>> getTraitsRating(@PathVariable("cid") int candidateId) {
		List<PanelTraitsRating> listOfTraitsRating = panelEvaluationService.getTraitsRating(candidateId);
		return new ResponseEntity<List<PanelTraitsRating>>(listOfTraitsRating,HttpStatus.OK);
	}
		
	
}