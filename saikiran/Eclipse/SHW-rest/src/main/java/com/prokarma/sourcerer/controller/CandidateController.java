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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.prokarma.sourcerer.dto.Candidate;
import com.prokarma.sourcerer.service.CandidateService;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
	
	private org.apache.log4j.Logger fileLogger = Logger.getLogger("logger.file");

	@Autowired
	private CandidateService candidateService;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ResponseEntity<String> addCandidate(@RequestParam("file") MultipartFile file,
			@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("number") String number, @RequestParam("technology") String technology) {
		byte[] bytes = null;
		try {
			bytes = file.getBytes();
		} catch (Exception exception) {
		fileLogger.error("unable to convert file"+exception.getMessage());
		}
		long phoneNumber = 0;
		try {
			phoneNumber = Long.parseLong(number);

		} catch (NumberFormatException numberFormatException) {
			fileLogger.error("NumberFormatException: " + numberFormatException.getMessage());
		}
		Candidate candidate = new Candidate();
		candidate.setCname(name);
		candidate.setEmail(email);
		candidate.setProfile(bytes);
		candidate.setPhoneNumber(phoneNumber);
		candidate.setTechnologyName(technology);
		Boolean result = candidateService.saveCandidate(candidate);
		if (result) {
			return new ResponseEntity<String>("Success",HttpStatus.OK);
			
		}
		return new ResponseEntity<String>("Failure",HttpStatus.OK);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Integer> authenticateUser(@RequestBody Candidate candidate) {
		Candidate candidateFromDB = candidateService.verifyCandidate(candidate);
		if(candidate.getToken()==candidateFromDB.getToken() && candidateFromDB.getIsVisited()==0)
		{
			return new ResponseEntity<Integer>(candidateFromDB.getCid(),HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(HttpStatus.NOT_FOUND);

	}
	
	@RequestMapping(value = "/candidatesToBeAssigned", method = RequestMethod.GET , produces = {"application/JSON"})
	public ResponseEntity<List<Candidate>> getCandidatesToBeAssigned() {
		return new ResponseEntity<List<Candidate>>(candidateService.getCandidatesToBeAssigned(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/candidatesRegistered", method = RequestMethod.GET , produces = {"application/JSON"})
	public ResponseEntity<List<Candidate>> getCandidatesWhoAreRegistered() {
		return new ResponseEntity<List<Candidate>>(candidateService.getCandidatesWhoAreRegistered(),HttpStatus.OK);
	}
	@RequestMapping(value = "/candidatesForSecondOpinion", method = RequestMethod.GET , produces = {"application/JSON"})
	public ResponseEntity<List<Candidate>> getCandidatesForSecondopinion() {
		return new ResponseEntity<List<Candidate>>(candidateService.getCandidatesForSecondopinion(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/candidateForsecondRound",method=RequestMethod.GET,produces= {"application/JSON"})
	public ResponseEntity<List<Candidate>> getCandidatesSelectedForSecondRound() {
		return new ResponseEntity<List<Candidate>>(candidateService.getCandidatesForSecondRound(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getCandidateProfile/{cid}", method = RequestMethod.GET)
	public ResponseEntity  <Object> getCandidateProfile(@PathVariable("cid") int cid){
		Candidate candidate = new Candidate();
		candidate.setCid(cid);
		Candidate candidateDetails=candidateService.getCandidateDetails(cid);
		return new ResponseEntity<Object>(candidateDetails.getProfile(),HttpStatus.OK);	
		
	}
	
	@RequestMapping(value = "/getCandidateDetails/{cid}", method = RequestMethod.GET)
	public ResponseEntity  <Candidate> getCandidateDetails(@PathVariable("cid") int cid){
		Candidate candidate = new Candidate();
		candidate.setCid(cid);
		Candidate candidateDetails=candidateService.getCandidateDetails(cid);
		return new ResponseEntity<Candidate>(candidateDetails,HttpStatus.OK);
		
	}
	

	
}
