package com.pkrm.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pkrm.event.dao.TrainDao;
import com.pkrm.event.model.Train;


@Service
public class TrainServiceImpl implements TrainService{
	
	
	@Autowired
	private TrainDao trainDao;
	
	public List<Train> getLikeTrainDetails(String trainName) {
		return trainDao.getLikeTrainDetails(trainName);
	}

}
