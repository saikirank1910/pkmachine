package com.prokarma.sourcerer.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prokarma.sourcerer.dao.PanelAssigneeDao;
import com.prokarma.sourcerer.dto.Email;
import com.prokarma.sourcerer.dto.PanelAssignee;
import com.prokarma.sourcerer.service.PanelAssigneeService;
import com.prokarma.sourcerer.utils.GenerateMessage;
import com.prokarma.sourcerer.utils.GenerateToken;
import com.prokarma.sourcerer.utils.SMTP;

@Service
public class PanelAssigneeServiceImpl implements PanelAssigneeService {

	final static org.apache.log4j.Logger FileLogger = Logger.getLogger("logger.file");

	@Autowired
	PanelAssigneeDao panelAssigneeDaoImpl;

	@Autowired
	private SMTP smtp;

	@Autowired
	private GenerateMessage generateMessage;

	public PanelAssignee verifyPanel(PanelAssignee panelAssignee) {
		List<PanelAssignee> list = panelAssigneeDaoImpl.verifyPanel(panelAssignee);

		for (PanelAssignee result : list) {
			if (result.getToken() == panelAssignee.getToken()) {
				panelAssignee = result;
				break;
			}
		}

		return panelAssignee;
	}

	public Boolean savePanelMember(PanelAssignee panelAssignee) {
		GenerateToken token = new GenerateToken();
		panelAssignee.setToken(token.generateToken());
		boolean result = false;
		try {
			result = panelAssigneeDaoImpl.savePanelMember(panelAssignee);
			if (result) {
				Email toPanel = generateMessage.sendEmailToPanel(panelAssignee);
				smtp.sendMail(toPanel);
				if (panelAssignee.getRound() == 1) {
					panelAssigneeDaoImpl.updatePanelAssigneeStatus(panelAssignee.getCandidateId(), 2);
				} else
					panelAssigneeDaoImpl.updatePanelAssigneeStatus(panelAssignee.getCandidateId(), 4);
			}

		} catch (Exception exception) {
			FileLogger.error("error in updating save Panel Member"+exception.getMessage());
		}
		return result;
	}

}
