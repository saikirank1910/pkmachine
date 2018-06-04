package com.prokarma.sourcerer.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.prokarma.sourcerer.dao.PanelDao;
import com.prokarma.sourcerer.dto.Panel;

@Repository
public class PanelDaoImpl implements PanelDao {
	private String addPanelSql = "insert into PANEL values(panelid_seq.nextVal,:panelmember,:panelemail,:paneltechnology)";
	private String getPanelMembersSql = "select * from panel where skill= :skill";
	private String getPanelDetailsSql = "select *from panel";
	private String editPanelDetailsSql = "update panel set panel_member=:name,email=:mail,skill=:skill  where panelid=:id";
	private String deletePanelMemberSql = "delete from panel where panelid=:id";

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void addPanel(Panel panel) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("panelmember", panel.getPanel_member());
		params.addValue("panelemail", panel.getEmail());
		params.addValue("paneltechnology", panel.getTechnology());
		namedParameterJdbcTemplate.update(addPanelSql, params);
	}

	public List<Panel> getPanelMembers(String skill) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("skill", skill);
		return namedParameterJdbcTemplate.query(getPanelMembersSql, params, new RowMapper<Panel>() {
			public Panel mapRow(ResultSet resultset, int index) throws SQLException {
				Panel panel = new Panel();
				panel.setEmail(resultset.getString(3));
				panel.setPanel_member(resultset.getString(2));
				panel.setPanelid(resultset.getInt(1));
				return panel;
			}

		});
	
	}

	public List<Panel> getPanelDetails() {
		return namedParameterJdbcTemplate.query(getPanelDetailsSql, new ResultSetExtractor<List<Panel>>() {
			public List<Panel> extractData(ResultSet resultSet) throws SQLException  {
				List<Panel> listOfPanelMembers = new ArrayList<Panel>();
				while (resultSet.next()) {
					final Panel panel = new Panel();
					panel.setPanelid(resultSet.getInt(1));
					panel.setPanel_member(resultSet.getString(2));
					panel.setEmail(resultSet.getString(3));
					panel.setTechnology(resultSet.getString(4));
					listOfPanelMembers.add(panel);
				}
				return listOfPanelMembers;
			}
		});
	}

	public boolean editPanelDetails(Panel panel) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", panel.getPanel_member());
		params.addValue("mail", panel.getEmail());
		params.addValue("skill", panel.getTechnology());
		params.addValue("id", panel.getPanelid());
		namedParameterJdbcTemplate.update(editPanelDetailsSql, params);
			return true;
	}

	public boolean deletePanelMember(Panel panel) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", panel.getPanelid());
		namedParameterJdbcTemplate.update(deletePanelMemberSql, params);
			return true;

	}

}