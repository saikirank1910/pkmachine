package com.prokarma.sourcerer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prokarma.sourcerer.dto.PanelAssignee;
import com.prokarma.sourcerer.service.PanelAssigneeService;

@RestController
@RequestMapping("/panelAssignee")
public class PanelAssigneeController {
	
	@Autowired
	private PanelAssigneeService panelAssigneeServiceImpl;
	
	@RequestMapping(value = "/panellogin", method = RequestMethod.POST)
	public ResponseEntity<PanelAssignee> authenticateUser(@RequestBody PanelAssignee panelAssignee) {
		PanelAssignee panelFromDataBase = panelAssigneeServiceImpl.verifyPanel(panelAssignee);
		
		if(panelAssignee.getToken()==panelFromDataBase.getToken() && panelFromDataBase.getIsVisited()==0)
		{
			return new ResponseEntity<PanelAssignee>(panelFromDataBase,HttpStatus.OK);
		}
		return new ResponseEntity<PanelAssignee>(HttpStatus.NO_CONTENT);
	}
	@RequestMapping(value = "/savePanel", method = RequestMethod.POST)
	public ResponseEntity<Void> savePanel(@RequestBody PanelAssignee panelAssignee){
		boolean result = panelAssigneeServiceImpl.savePanelMember(panelAssignee);
		if(result)
			return new ResponseEntity<Void>(HttpStatus.OK);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	
	
	
}