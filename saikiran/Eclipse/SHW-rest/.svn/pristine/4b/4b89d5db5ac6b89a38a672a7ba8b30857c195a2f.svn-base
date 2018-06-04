package com.prokarma.sourcerer.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;

import com.prokarma.sourcerer.dao.PanelEvaluationDao;
import com.prokarma.sourcerer.dto.PanelEvaluation;
import com.prokarma.sourcerer.dto.PanelSkillsRating;
import com.prokarma.sourcerer.dto.PanelTraitsRating;

@Component
public class PanelEvaluationDaoImpl implements PanelEvaluationDao {
	private String addEvaluationSql = "insert into panel_feedback values(eval_seq.nextVal,:candidateId,:panelId,:comments,:skillLevel,:overrallRating,:status)";
	private String addSkillsRatingSql = "insert into panel_feedback_detail_skills(pfdsid,tsid,ratingid,panelid,cid) values (skill_seq.nextval,:skillId,:ratingId,:pid,:cid)";
	private String addTraitsRatingSql = "insert into personal_traits_rating(ptrid,ratingid,skillid,cid,pid) values (traits_seq.nextval,:ratingId,:skillId,:cid,:pid)";
	private String changePanelStatusSql = "update panel_assignee set isvisited=:visited where pid=:pid and cid=:cid";
	private String getskillRatingSql = "select unique p.ratingid,t.TECHNICLESUBSKILL,p.panelid from PANEL_FEEDBACK_DETAIL_SKILLS p,techinicle_sub_skills t,PANEL_ASSIGNEE c where p.tsid=t.tsskillid and p.cid=c.cid and p.cid=:cid and c.round=1";
	private String getTraitsRatingSql = "select unique b.ratingid,a.personal_traits,b.pid from PERSONAL_TRAITS_RATING b, PERSONAL_TRAITS a,PANEL_ASSIGNEE c where b.skillid=a.traitid and b.cid=:cid and c.round=1";
	private String getEvaluationDetailsSql = "select a.commnets,a.skilllevel,a.overall_rating,a.status,b.pid,(select panel_member from panel where panelid=b.pid) from panel_feedback a,PANEL_ASSIGNEE b where a.panel_member_id=b.pid and a.CANDIDATEID=b.cid and b.cid=:cid and b.round=1";

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public Boolean addEvaluation(PanelEvaluation panelEvaluation) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("candidateId", panelEvaluation.getCandidateId());
		params.addValue("panelId", panelEvaluation.getPanelId());
		params.addValue("comments", panelEvaluation.getComment());
		params.addValue("skillLevel", panelEvaluation.getSkillLevel());
		params.addValue("overrallRating", panelEvaluation.getOverall_rating());
		params.addValue("status", panelEvaluation.getStatus());
		 namedParameterJdbcTemplate.update(addEvaluationSql, params);

		return true;
	}

	public Boolean addSkillsRating(List<PanelSkillsRating> list) {
		SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(list.toArray());
		namedParameterJdbcTemplate.batchUpdate(addSkillsRatingSql, batch);
		
		return true;
	}

	public Boolean addTraitsRating(List<PanelTraitsRating> list) {
		SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(list.toArray());
		 namedParameterJdbcTemplate.batchUpdate(addTraitsRatingSql, batch);
	
		return true;
	}

	public boolean changePanelStatus(PanelEvaluation panelEvaluation) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("cid", panelEvaluation.getCandidateId());
		params.addValue("pid", panelEvaluation.getPanelId());
		params.addValue("visited", 1);

		namedParameterJdbcTemplate.update(changePanelStatusSql, params);
		
			return true;
		
	}

	public List<PanelSkillsRating> getskillRating(int candidateId) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("cid", candidateId);
		return namedParameterJdbcTemplate.query(getskillRatingSql, params, new RowMapper<PanelSkillsRating>() {
			public PanelSkillsRating mapRow(ResultSet resultset, int index) throws SQLException {
				PanelSkillsRating rating = new PanelSkillsRating();
				rating.setRatingId(resultset.getInt(1));
				rating.setTechnologyName(resultset.getString(2));
				rating.setPid(resultset.getShort(3));
				return rating;
			}

		});
	}

	public List<PanelTraitsRating> getTraitsRating(int candidateId) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("cid", candidateId);
		return namedParameterJdbcTemplate.query(getTraitsRatingSql, params, new RowMapper<PanelTraitsRating>() {
			public PanelTraitsRating mapRow(ResultSet resultset, int index) throws SQLException {
				PanelTraitsRating rating = new PanelTraitsRating();
				rating.setRatingId(resultset.getInt(1));
				rating.setTraitName(resultset.getString(2));
				rating.setPid(resultset.getShort(3));
				return rating;
			}

		});
	}

	public List<PanelEvaluation> getEvaluationDetails(int candidateId) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("cid", candidateId);
		return namedParameterJdbcTemplate.query(getEvaluationDetailsSql, params,
				new RowMapper<PanelEvaluation>() {

					public PanelEvaluation mapRow(ResultSet resultSet, int index) throws SQLException {
						final PanelEvaluation evaluationList = new PanelEvaluation();
						evaluationList.setComment(resultSet.getString(1));
						evaluationList.setSkillLevel(resultSet.getString(2));
						evaluationList.setOverall_rating(resultSet.getString(3));
						evaluationList.setStatus(resultSet.getString(4));
						evaluationList.setPanelId(resultSet.getInt(5));
						evaluationList.setPanelMemberName(resultSet.getString(6));
						return evaluationList;
					}

				});
	}

	public PanelEvaluation getPanelMemberAndCandidateName(PanelEvaluation panelEvaluation) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("cid", panelEvaluation.getCandidateId());
		params.addValue("pid", panelEvaluation.getPanelId());
		return namedParameterJdbcTemplate.queryForObject(
				"select (select cnname from candidate where cid=:cid),(select panel_member from panel where panelid=:pid),commnets,skilllevel,overall_rating,status from panel_feedback where CANDIDATEID=:cid and panel_member_id=:pid ",
				params, new RowMapper<PanelEvaluation>() {

					public PanelEvaluation mapRow(ResultSet resultSet, int index) throws SQLException {
						final PanelEvaluation evaluationList = new PanelEvaluation();
						evaluationList.setCandidateName(resultSet.getString(1));
						evaluationList.setPanelMemberName(resultSet.getString(2));
						evaluationList.setComment(resultSet.getString(3));
						evaluationList.setSkillLevel(resultSet.getString(4));
						evaluationList.setOverall_rating(resultSet.getString(5));
						evaluationList.setStatus(resultSet.getString(6));
						return evaluationList;
					}

				});
	}

}
