package com.prokarma.sourcerer.service;

import java.sql.SQLException;
import java.util.List;

import com.prokarma.sourcerer.dto.Panel;

public interface PanelService {
	public boolean panelService(Panel panel) throws SQLException;

	public List<Panel> getPanelMembers(Panel panel);

	public List<Panel> getPanelDetails();

	public boolean editPanelDetails(Panel panel);

	public boolean deletePanelMember(Panel panel);
}
