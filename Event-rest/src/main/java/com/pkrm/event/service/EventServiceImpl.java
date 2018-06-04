package com.pkrm.event.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pkrm.event.dao.EventDao;
import com.pkrm.event.model.Event;


@Service
public class EventServiceImpl implements EventService{
	
	
	@Autowired
	private EventDao eventDao;

	public int saveEvent(Event event) {
		int result=eventDao.saveEvent(event);
		System.out.println(result);
		return result;
	}

}
