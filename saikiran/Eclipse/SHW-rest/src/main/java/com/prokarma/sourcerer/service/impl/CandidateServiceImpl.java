package com.prokarma.sourcerer.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prokarma.sourcerer.dao.CandidateDao;
import com.prokarma.sourcerer.dao.TasksDao;
import com.prokarma.sourcerer.dto.Candidate;
import com.prokarma.sourcerer.dto.Email;
import com.prokarma.sourcerer.dto.Subtechnology;
import com.prokarma.sourcerer.service.CandidateService;
import com.prokarma.sourcerer.utils.GenerateMessage;
import com.prokarma.sourcerer.utils.GenerateToken;
import com.prokarma.sourcerer.utils.SMTP;

@Service
public class CandidateServiceImpl implements CandidateService {
	final static org.apache.log4j.Logger FileLogger = Logger.getLogger("logger.file");

	@Autowired
	private CandidateDao candidateDao;

	@Autowired
	private TasksDao tasksDao;

	@Autowired
	private SMTP smtp;

	@Autowired
	private GenerateMessage generateMessage;

	public Boolean saveCandidate(Candidate candidate) {
		GenerateToken token = new GenerateToken();
		candidate.setToken(token.generateToken());
		candidate.setIsVisited(0);
		Boolean result = false;
		try {
			result = candidateDao.save(candidate);
			if (result) {
				Email toCandidate = generateMessage.sendEmailToCandidate(candidate);
				smtp.sendMail(toCandidate);
			}
		} catch (Exception exception) {
			FileLogger.error("error while adding a candidate" + exception.getMessage());
		}
		return result;
	}

	public List<Subtechnology> returnTechnologies(int id) {
		Candidate candidate = candidateDao.findTechnologyName(id);
		List<Subtechnology> list = tasksDao.getSubtechnologiesOfParticularSkill(candidate.getTechnologyName());
		return list;
	}

	public Candidate verifyCandidate(Candidate candidate) {
		Candidate candidateFromDB = candidateDao.verifyCandidate(candidate);
		return candidateFromDB;
	}

	public Boolean restrictCandidate(int cid) {
		boolean result = candidateDao.restrictCandidate(cid);
		return result;

	}

	public List<Candidate> getCandidatesToBeAssigned() {
		return candidateDao.getCandidatesToBeAssigned();
	}

	public List<Candidate> getCandidatesWhoAreRegistered() {
		return candidateDao.getCandidatesWhoAreRegistered();
	}

	public List<Candidate> getCandidatesForSecondopinion() {
		return candidateDao.getCandidatesForSecondopinion();
	}

	public Candidate getCandidateDetails(int cid) {
		return candidateDao.getCandidateDetails(cid);
	}

	public List<Candidate> getCandidatesForSecondRound() {
		return candidateDao.getCandidatesForSecondRound();
	}

}
