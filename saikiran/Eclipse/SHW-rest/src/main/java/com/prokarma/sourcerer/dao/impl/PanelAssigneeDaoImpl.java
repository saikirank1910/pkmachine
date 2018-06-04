package com.prokarma.sourcerer.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.prokarma.sourcerer.dao.PanelAssigneeDao;
import com.prokarma.sourcerer.dto.PanelAssignee;
import com.prokarma.sourcerer.dto.PanelEvaluation;

@Component
public class PanelAssigneeDaoImpl implements PanelAssigneeDao {

	private String verifyPanelSql = "select pid,cid,isvisited,token,round from panel_assignee where panel_email=:email";
	private String savePanelMemberSql = "insert into panel_assignee values(ASSIGNE_SEQ.nextval,:pid,:cid,:token,0,:email,:modeOfInterview,:round)";
	private String updatePanelAssigneeStatusSql = "update candidate set isvisited = :status  where cid= :id";
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<PanelAssignee> verifyPanel(PanelAssignee panelAssignee) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("email", panelAssignee.getPanelEmail());
		return namedParameterJdbcTemplate.query(verifyPanelSql, params, new RowMapper<PanelAssignee>() {
			public PanelAssignee mapRow(ResultSet resultSet, int arg1) throws SQLException {
				PanelAssignee panelAssignee = new PanelAssignee();
				panelAssignee.setCandidateId(resultSet.getInt(2));
				panelAssignee.setPanelId(resultSet.getInt(1));
				panelAssignee.setIsVisited(resultSet.getInt(3));
				panelAssignee.setToken(resultSet.getInt(4));
				panelAssignee.setRound(resultSet.getInt(5));
				return panelAssignee;
			}
		});
	}

	public Boolean savePanelMember(PanelAssignee panelAssignee) {

		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("pid", panelAssignee.getPanelId());
		params.addValue("cid", panelAssignee.getCandidateId());
		params.addValue("token", panelAssignee.getToken());
		params.addValue("email", panelAssignee.getPanelEmail());
		params.addValue("modeOfInterview", panelAssignee.getModeOfInterview());
		params.addValue("round", panelAssignee.getRound());
		namedParameterJdbcTemplate.update(savePanelMemberSql, params);
			return true;

	}

	public Boolean updatePanelAssigneeStatus(int cid, int status) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("status", status);
		params.addValue("id", cid);
		namedParameterJdbcTemplate.update(updatePanelAssigneeStatusSql, params);
			return true;
	}

	public void updateCandidateSecondRound(PanelEvaluation panelEvaluation) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("cid", panelEvaluation.getCandidateId());
		params.addValue("pid", panelEvaluation.getPanelId());
		namedParameterJdbcTemplate.update("update candidate set isvisited=4 where cid=(select cid from panel_assignee where cid=:cid and pid=:pid and round=2)", params);
	}
}
