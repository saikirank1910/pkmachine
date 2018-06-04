package com.prokarma.sourcerer.controller;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prokarma.sourcerer.dto.Panel;
import com.prokarma.sourcerer.service.PanelService;

@RestController
@RequestMapping("/panel")
public class PanelController {

	@Autowired
	PanelService panelimpl;

	@RequestMapping(value = "/addPanel", method = RequestMethod.POST)
	public ResponseEntity<Object> addPanel(@RequestBody Panel panel) throws SQLException {
		boolean success = panelimpl.panelService(panel);
		if (success) {
			return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Object>(HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@RequestMapping(value = "/getPanel", method = RequestMethod.POST)
	public ResponseEntity<List<Panel>> getPanelMembers(@RequestBody Panel panel) {
		List<Panel> panelMembers = panelimpl.getPanelMembers(panel);
		if(CollectionUtils.isNotEmpty(panelMembers))
		{
		return new ResponseEntity<List<Panel>>(panelMembers, HttpStatus.OK);
		}
		else
			return new ResponseEntity<List<Panel>>(panelMembers, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/getAllPanelDetails", method = RequestMethod.GET, produces = { "application/JSON" })
	public ResponseEntity<List<Panel>> getAllUsers() {
		List<Panel> listOfPanelDetails = panelimpl.getPanelDetails();
		return new ResponseEntity<List<Panel>>(listOfPanelDetails, HttpStatus.OK);

	}

	@RequestMapping(value = "/editPanelDetails", method = RequestMethod.POST)
	public ResponseEntity<Void> editUser(@RequestBody Panel panel) {
		Boolean result = panelimpl.editPanelDetails(panel);
		if (result) {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}

	}

	@RequestMapping(value = "/deletePanelDetails", method = RequestMethod.POST)
	public ResponseEntity<Void> deleteUser(@RequestBody Panel panel) {
		Boolean result = panelimpl.deletePanelMember(panel);
		if (result) {
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

	}

}