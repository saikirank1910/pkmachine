package com.prokarma.sourcerer.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.prokarma.sourcerer.dao.TasksDao;
import com.prokarma.sourcerer.dto.Subtechnology;
import com.prokarma.sourcerer.dto.Technology;

@Repository
public class TasksDaoImpl implements TasksDao {
	private String getTechnologiesSql = "select technicleskillname from technicle_skills";
	private String getSubtechnologiesSql = "select m.TECHNICLESKILLNAME,t.tsskillid,t.TECHNICLESUBSKILL,t.description from technicle_skills m,techinicle_sub_skills t where m.tskillid=t.tskillid";
	private String addTechnologySql = "insert into technicle_skills values(seq_skill.nextval,:technology)";
	private String addSubtechnologySql = "insert into techinicle_sub_skills values(seq_subskill.nextval,(select tskillid from technicle_skills where technicleskillname= :technology),:subtechnology , :description)";
	private String editSubtechnologySql = "update techinicle_sub_skills set TECHNICLESUBSKILL = :subtechnology ,description = :description where tsskillid= :id";
	private String deleteSubtechnologySql = "delete from techinicle_sub_skills  where tsskillid= :id";
	private String getSubtechnologiesOfParticularSkillSql = "select t.tsskillid,t.techniclesubskill ,t.description from technicle_skills m,techinicle_sub_skills t where m.tskillid=t.tskillid and m.TECHNICLESKILLNAME=:name";
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<String> getTechnologies() {
		return namedParameterJdbcTemplate.query(getTechnologiesSql, new RowMapper<String>() {
			public String mapRow(ResultSet resultset, int index) throws SQLException {
				return resultset.getString(1);
			}
		});
	
	}

	public List<Subtechnology> getSubtechnologies() {

		return namedParameterJdbcTemplate.query(getSubtechnologiesSql, new RowMapper<Subtechnology>() {

			public Subtechnology mapRow(ResultSet resultset, int index) throws SQLException {
				Subtechnology subTechnology = new Subtechnology();
				subTechnology.setId(resultset.getInt(2));
				subTechnology.setTechnology(resultset.getString(1));
				subTechnology.setSubTechnology(resultset.getString(3));
				subTechnology.setDescription(resultset.getString(4));
				return subTechnology;
			}

		});
	}

	public boolean addTechnology(Technology technology) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("technology", technology.getName());
		namedParameterJdbcTemplate.update(addTechnologySql, params);

			return true;
	}

	public boolean addSubtechnology(Subtechnology subTechnology) {

		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("technology", subTechnology.getTechnology());
		params.addValue("subtechnology", subTechnology.getSubTechnology());
		params.addValue("description", subTechnology.getDescription());
		namedParameterJdbcTemplate.update(addSubtechnologySql, params);

			return true;
	}

	public boolean editSubtechnology(Subtechnology subTechnology) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("subtechnology", subTechnology.getSubTechnology());
		params.addValue("id", subTechnology.getId());
		params.addValue("description", subTechnology.getDescription());
		namedParameterJdbcTemplate.update(editSubtechnologySql, params);
		
			return true;
	}

	public boolean deleteSubtechnology(int id) {

		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		 namedParameterJdbcTemplate.update(deleteSubtechnologySql, params);
			return true;
	}

	public List<Subtechnology> getSubtechnologiesOfParticularSkill(String name) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", name);
		return namedParameterJdbcTemplate.query(getSubtechnologiesOfParticularSkillSql, params,
				new RowMapper<Subtechnology>() {
					public Subtechnology mapRow(ResultSet resultset, int index) throws SQLException {
						Subtechnology subTechnology = new Subtechnology();
						subTechnology.setId(resultset.getInt(1));
						subTechnology.setSubTechnology(resultset.getString(2));
						subTechnology.setDescription(resultset.getString(3));
						return subTechnology;
					}
				});
	}
}
