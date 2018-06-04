package com.prokarma.sourcerer.dao;

import java.util.List;

import com.prokarma.sourcerer.dto.PanelEvaluation;
import com.prokarma.sourcerer.dto.PanelSkillsRating;
import com.prokarma.sourcerer.dto.PanelTraitsRating;

public interface PanelEvaluationDao {
	public Boolean addEvaluation(PanelEvaluation panelEvaluation);
	public Boolean addSkillsRating(List<PanelSkillsRating> list);
	public Boolean addTraitsRating(List<PanelTraitsRating> list);
	public boolean changePanelStatus(PanelEvaluation panelEvaluation);
	public List<PanelSkillsRating> getskillRating(int candidateId);
	public List<PanelTraitsRating> getTraitsRating(int candidateId);
	public List<PanelEvaluation> getEvaluationDetails(int candidateId);
	public PanelEvaluation getPanelMemberAndCandidateName(PanelEvaluation panelEvaluation);
}