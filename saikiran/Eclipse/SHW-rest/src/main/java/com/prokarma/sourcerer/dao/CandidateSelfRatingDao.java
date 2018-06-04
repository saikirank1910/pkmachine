package com.prokarma.sourcerer.dao;

import java.util.List;

import com.prokarma.sourcerer.dto.CandidateSelfRating;

public interface CandidateSelfRatingDao {
	public Boolean insertSelfRating(List<CandidateSelfRating> list);

	public List<CandidateSelfRating> getSelfRating(int cid);


	
	
}
