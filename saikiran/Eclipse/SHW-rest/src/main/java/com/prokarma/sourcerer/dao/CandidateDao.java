package com.prokarma.sourcerer.dao;

import java.util.List;

import com.prokarma.sourcerer.dto.Candidate;
import com.prokarma.sourcerer.dto.PanelEvaluation;

public interface CandidateDao {
	public boolean save(Candidate candidate);
	public Candidate findTechnologyName(int id);
	public Candidate verifyCandidate(Candidate candidate);
	public Boolean restrictCandidate(int cid);
	public List<Candidate> getCandidatesToBeAssigned();
	public List<Candidate> getCandidatesWhoAreRegistered();
	public List<Candidate> getCandidatesForSecondopinion();
	public Candidate getCandidateDetails(int cid);
	public List<Candidate> getCandidatesForSecondRound();
	public void changeCandidateStatus(PanelEvaluation panelEvaluation);
	public Candidate getRoundOfCandidateId(PanelEvaluation panelEvaluation);
}