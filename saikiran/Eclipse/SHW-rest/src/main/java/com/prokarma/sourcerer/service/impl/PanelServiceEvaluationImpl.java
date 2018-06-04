package com.prokarma.sourcerer.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prokarma.sourcerer.dao.CandidateDao;
import com.prokarma.sourcerer.dao.PanelAssigneeDao;
import com.prokarma.sourcerer.dao.PanelEvaluationDao;
import com.prokarma.sourcerer.dto.Candidate;
import com.prokarma.sourcerer.dto.Email;
import com.prokarma.sourcerer.dto.PanelEvaluation;
import com.prokarma.sourcerer.dto.PanelSkillsRating;
import com.prokarma.sourcerer.dto.PanelTraitsRating;
import com.prokarma.sourcerer.service.PanelEvaluationService;
import com.prokarma.sourcerer.utils.GenerateMessage;
import com.prokarma.sourcerer.utils.SMTP;

@Component
public class PanelServiceEvaluationImpl implements PanelEvaluationService {

	final static org.apache.log4j.Logger FileLogger = Logger.getLogger("logger.file");

	@Autowired
	private SMTP smtp;

	@Autowired
	private PanelEvaluationDao panelEvaluationDao;
	
	@Autowired
	private PanelAssigneeDao panelAssigneeDao;

	@Autowired
	private CandidateDao candidateDao;

	public Boolean addEvaluation(PanelEvaluation panelEvaluation) {
		if ("select".equals(panelEvaluation.getStatus())) {
			candidateDao.changeCandidateStatus(panelEvaluation);
			panelAssigneeDao.updateCandidateSecondRound(panelEvaluation);
		}else {
			Candidate candidate=candidateDao.getRoundOfCandidateId(panelEvaluation);
			int round=candidate.getIsVisited();
			if(round==2)
				panelAssigneeDao.updatePanelAssigneeStatus(panelEvaluation.getCandidateId(), 3);
		}
		panelEvaluationDao.changePanelStatus(panelEvaluation);
		boolean result = panelEvaluationDao.addEvaluation(panelEvaluation);
		PanelEvaluation panelEvaluationMail = panelEvaluationDao.getPanelMemberAndCandidateName(panelEvaluation);
		GenerateMessage generateMailToSuperAdmin = new GenerateMessage();
		Email emailToSuperadmin = generateMailToSuperAdmin
				.sendMailToSuperAdminRegardingPanelFeedbackStatus(panelEvaluationMail);
		smtp.sendMail(emailToSuperadmin);
		return result;
	}

	public Boolean addSkillsRating(List<PanelSkillsRating> list) {
		return panelEvaluationDao.addSkillsRating(list);
	}

	public Boolean addTraitsRating(List<PanelTraitsRating> list) {
		return panelEvaluationDao.addTraitsRating(list);
	}

	public List<PanelSkillsRating> getskillRating(int candidateId) {
		return panelEvaluationDao.getskillRating(candidateId);
	}

	public List<PanelTraitsRating> getTraitsRating(int candidateId) {
		return panelEvaluationDao.getTraitsRating(candidateId);
	}

	public List<PanelEvaluation> getEvaluationDetails(int candidateId) {
		try {
			return panelEvaluationDao.getEvaluationDetails(candidateId);
		} catch (Exception exception) {
			FileLogger.error("error while retrieving candidate data..!" + exception.getMessage());
			return null;
		}
	}

}