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

import com.prokarma.sourcerer.dto.Candidate;
import com.prokarma.sourcerer.dto.CandidateSelfRating;
import com.prokarma.sourcerer.dto.Subtechnology;
import com.prokarma.sourcerer.service.CandidateSelfRatingService;
import com.prokarma.sourcerer.service.CandidateService;

@RestController
@RequestMapping("/rating")
public class CandidateSelfRatingController {

	final  org.apache.log4j.Logger fileLogger = Logger.getLogger("logger.file");

	@Autowired
	private CandidateService candidateService;

	@Autowired
	private CandidateSelfRatingService candidateSelfRatingService;

	@RequestMapping(value = "/id/{cid}", method = RequestMethod.GET, produces = { "application/JSON" })
	public ResponseEntity<List<Subtechnology>> getTechnologiesForCandidateId(@PathVariable("cid") int cid) {
		Candidate candidate = new Candidate();
		candidate.setCid(cid);
		List<Subtechnology> list = candidateService.returnTechnologies(candidate.getCid());
		return new ResponseEntity<List<Subtechnology>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public ResponseEntity<List<Subtechnology>> submitTechnologyRating(@RequestBody List<CandidateSelfRating> list) {

		Boolean result = candidateSelfRatingService.insertSelfRating(list);
		if (result) {
			candidateService.restrictCandidate(list.get(0).getCid());
			return new ResponseEntity<List<Subtechnology>>(HttpStatus.OK);
		} else
			return new ResponseEntity<List<Subtechnology>>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/getSelfAssessment/{cid}", method = RequestMethod.GET, produces = { "application/JSON" })
	public ResponseEntity<List<CandidateSelfRating>> getSelfRating(@PathVariable("cid") int cid) {
			List<CandidateSelfRating> candidateSelfRating = candidateSelfRatingService.getSelfRating(cid);
			if(candidateSelfRating==null) {
				return new ResponseEntity<List<CandidateSelfRating>>(HttpStatus.NOT_FOUND);
				}
			return new ResponseEntity<List<CandidateSelfRating>>(candidateSelfRating, HttpStatus.OK);

	}

}