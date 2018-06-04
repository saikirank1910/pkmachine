package com.pkrm.event.dao;

import java.util.List;

import com.pkrm.event.model.Train;

public interface TrainDao {
	public List<Train> getLikeTrainDetails(String trainName);
}
