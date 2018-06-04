package com.pkrm.event.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pkrm.event.model.Train;
import com.pkrm.event.service.TrainService;

@RestController
@RequestMapping("/train")
public class TrainController {
	
	@Autowired
	private TrainService trainService;
	
	@RequestMapping(value = "/{trainName}", method = RequestMethod.GET)
	public ResponseEntity<List<Train>> getSourceDetails(@PathVariable String trainName) {
		List<Train> trainDetails = trainService.getLikeTrainDetails(trainName);
		return new ResponseEntity<List<Train>>(trainDetails,HttpStatus.OK);
	}
}
