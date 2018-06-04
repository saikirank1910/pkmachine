package com.prokarma.sourcerer.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prokarma.sourcerer.dao.PanelDao;
import com.prokarma.sourcerer.dto.Panel;
import com.prokarma.sourcerer.service.PanelService;

@Service
public class PanelImpl implements PanelService {

	final static org.apache.log4j.Logger FileLogger = Logger.getLogger("logger.file");

	@Autowired
	PanelDao panelDaoImpl;

	public boolean panelService(Panel panel) throws SQLException {
		boolean success = false;
		try {
			panelDaoImpl.addPanel(panel);
			success = true;
		} catch (Exception exception) {
			success = false;
			FileLogger.error("error while user adding a panel" + exception.getMessage());
		}
		return success;
	}

	public List<Panel> getPanelMembers(Panel panel) {
		return panelDaoImpl.getPanelMembers(panel.getTechnology());
	}

	public List<Panel> getPanelDetails() {
		return panelDaoImpl.getPanelDetails();
	}

	public boolean editPanelDetails(Panel panel) {
		return panelDaoImpl.editPanelDetails(panel);
	}

	public boolean deletePanelMember(Panel panel) {
		return panelDaoImpl.deletePanelMember(panel);
	}

}