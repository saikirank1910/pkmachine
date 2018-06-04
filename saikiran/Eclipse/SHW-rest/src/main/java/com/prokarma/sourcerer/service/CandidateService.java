package com.prokarma.sourcerer.service;

import java.util.List;

import com.prokarma.sourcerer.dto.Candidate;
import com.prokarma.sourcerer.dto.Subtechnology;

public interface CandidateService {
	public Boolean saveCandidate(Candidate candidate);
	public List<Subtechnology> returnTechnologies(int id);
	public Candidate verifyCandidate(Candidate candidate);
	public Boolean restrictCandidate(int cid);
	public List<Candidate> getCandidatesToBeAssigned();
	public List<Candidate> getCandidatesWhoAreRegistered();
	public List<Candidate> getCandidatesForSecondopinion();
	public Candidate getCandidateDetails(int cid);
	public List<Candidate> getCandidatesForSecondRound();



}