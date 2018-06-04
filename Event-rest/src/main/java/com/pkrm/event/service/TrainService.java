package com.pkrm.event.service;

import java.util.List;

import com.pkrm.event.model.Train;

public interface TrainService {
	public List<Train> getLikeTrainDetails(String trainName);
}
