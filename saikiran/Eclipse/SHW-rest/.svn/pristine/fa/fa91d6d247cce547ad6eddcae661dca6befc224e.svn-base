package com.prokarma.sourcerer.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prokarma.sourcerer.dao.CandidateSelfRatingDao;
import com.prokarma.sourcerer.dto.CandidateSelfRating;
import com.prokarma.sourcerer.service.CandidateSelfRatingService;

@Component
public class CandidateSelfRatingImpl implements CandidateSelfRatingService {

	final  org.apache.log4j.Logger FileLogger = Logger.getLogger("logger.file");

	@Autowired
	private CandidateSelfRatingDao candidateSelfRatingDao;

	public boolean insertSelfRating(List<CandidateSelfRating> list) {
		return candidateSelfRatingDao.insertSelfRating(list);
	}

	public List<CandidateSelfRating> getSelfRating(int cid) {
		List<CandidateSelfRating> candidateSelfRating=null;
		try {
			candidateSelfRating = candidateSelfRatingDao.getSelfRating(cid);
			return candidateSelfRating;
		} catch (Exception exception) {
			FileLogger.error("Exception while retrieving candidate self rating "+exception.getMessage());
			return candidateSelfRating;
		}
		

	}

}
