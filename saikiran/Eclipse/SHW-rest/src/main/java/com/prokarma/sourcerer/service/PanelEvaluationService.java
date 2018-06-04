package com.prokarma.sourcerer.service;

import java.util.List;

import com.prokarma.sourcerer.dto.PanelEvaluation;
import com.prokarma.sourcerer.dto.PanelSkillsRating;
import com.prokarma.sourcerer.dto.PanelTraitsRating;

public interface PanelEvaluationService {
	public Boolean addEvaluation(PanelEvaluation panelEvaluation);
	public Boolean addSkillsRating(List<PanelSkillsRating> list);
	public Boolean addTraitsRating(List<PanelTraitsRating> list);
	public List<PanelSkillsRating> getskillRating(int candidateId);
	public List<PanelTraitsRating> getTraitsRating(int candidateId);
	public List<PanelEvaluation> getEvaluationDetails(int candidateId);

}
