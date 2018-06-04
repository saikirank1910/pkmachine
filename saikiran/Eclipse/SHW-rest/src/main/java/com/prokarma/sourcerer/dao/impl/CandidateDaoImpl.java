package com.prokarma.sourcerer.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.prokarma.sourcerer.dao.CandidateDao;
import com.prokarma.sourcerer.dto.Candidate;
import com.prokarma.sourcerer.dto.PanelEvaluation;

@Component
public class CandidateDaoImpl implements CandidateDao {
	private String insertCandidateSql = "insert into candidate values (CID_SEQ.nextval,:name,:profile,:email,:number,:technologyName,:isVisited,:token)";
	private String findTechnologyByNameSql = "select technologyname from candidate where CID=:id";
	private String verifyCandidateSql = "select tokenid,cid,isvisited from candidate where email = :email";
	private String restrictCandidateSql = "update candidate set isvisited=:visited where cid=:cid";
	private String getCandidatesToBeAssignedSql = "select cid,cnname,email,technologyname from candidate where isvisited=:id";
	private String getCandidatesWhoAreRegisteredSql = "select cid,cnname,email,technologyname from candidate where isvisited=:id";
	private String getCandidatesForSecondopinionSql = "select c.cid,c.cnname,c.email,c.technologyname from candidate c , panel_feedback f where c.cid=f.candidateid and c.isvisited=:id and status!='select'";
	private String getCandidateDetailsSql = "select cnname,profile,email,phonenumber,technologyname from candidate where CID=:cid";
	private String getCandidatesForSecondRoundSql = "select c.cid, c.cnname,c.email,c.technologyname from candidate c where c.isvisited=3";
	private String changeCandidateStatusSql = "update candidate set isvisited=:status where cid=:candidateId";

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public boolean save(Candidate candidate) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", candidate.getCname());
		params.addValue("profile", candidate.getProfile());
		params.addValue("email", candidate.getEmail());
		params.addValue("number", candidate.getPhoneNumber());
		params.addValue("technologyName", candidate.getTechnologyName());
		params.addValue("isVisited", candidate.getIsVisited());
		params.addValue("token", candidate.getToken());
		namedParameterJdbcTemplate.update(insertCandidateSql, params);
	
			return true;
	
	}

	public Candidate findTechnologyName(int id) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		return namedParameterJdbcTemplate.queryForObject(findTechnologyByNameSql, params, new RowMapper<Candidate>() {
			public Candidate mapRow(ResultSet rs, int arg1) throws SQLException {
				Candidate obj = new Candidate();
				obj.setTechnologyName(rs.getString(1));
				return obj;
			}

		});
	}

	public Candidate verifyCandidate(Candidate candidate) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("email", candidate.getEmail());
		params.addValue("cid", candidate.getCid());
		return namedParameterJdbcTemplate.queryForObject(verifyCandidateSql, params, new RowMapper<Candidate>() {
			public Candidate mapRow(ResultSet resultSet, int arg1) throws SQLException {
				Candidate candidate = new Candidate();
				candidate.setToken(resultSet.getInt(1));
				candidate.setCid(resultSet.getInt(2));
				candidate.setIsVisited(resultSet.getInt(3));
				return candidate;
			}

		});

	}

	public Boolean restrictCandidate(int cid) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("cid", cid);
		params.addValue("visited", 1);
		namedParameterJdbcTemplate.update(restrictCandidateSql, params);
		return true;
	}

	public java.util.List<Candidate> getCandidatesToBeAssigned() {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", 1);
		return namedParameterJdbcTemplate.query(getCandidatesToBeAssignedSql, params, new RowMapper<Candidate>() {

			public Candidate mapRow(ResultSet resultset, int index) throws SQLException {
				Candidate candidate = new Candidate();
				candidate.setCid(resultset.getInt(1));
				candidate.setCname(resultset.getString(2));
				candidate.setEmail(resultset.getString(3));
				candidate.setTechnologyName(resultset.getString(4));
				return candidate;
			}

		});
	}

	public List<Candidate> getCandidatesWhoAreRegistered() {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", 0);
		return namedParameterJdbcTemplate.query(getCandidatesWhoAreRegisteredSql, params, new RowMapper<Candidate>() {

			public Candidate mapRow(ResultSet resultset, int index) throws SQLException {
				Candidate candidate = new Candidate();
				candidate.setCid(resultset.getInt(1));
				candidate.setCname(resultset.getString(2));
				candidate.setEmail(resultset.getString(3));
				candidate.setTechnologyName(resultset.getString(4));
				return candidate;
			}

		});
	}

	public List<Candidate> getCandidatesForSecondopinion() {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", 2);
		return namedParameterJdbcTemplate.query(getCandidatesForSecondopinionSql, params, new RowMapper<Candidate>() {

			public Candidate mapRow(ResultSet resultset, int index) throws SQLException {
				Candidate candidate = new Candidate();
				candidate.setCid(resultset.getInt(1));
				candidate.setCname(resultset.getString(2));
				candidate.setEmail(resultset.getString(3));
				candidate.setTechnologyName(resultset.getString(4));
				return candidate;
			}

		});
	}

	public Candidate getCandidateDetails(int cid) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("cid", cid);
		return namedParameterJdbcTemplate.queryForObject(getCandidateDetailsSql, params, new RowMapper<Candidate>() {
			public Candidate mapRow(ResultSet rs, int arg1) throws SQLException {
				Candidate obj = new Candidate();
				obj.setCname(rs.getString(1));
				obj.setProfile(rs.getBytes(2));
				obj.setEmail(rs.getString(3));
				obj.setPhoneNumber(rs.getLong(4));
				obj.setTechnologyName(rs.getString(5));
				return obj;
			}

		});
	}

	public List<Candidate> getCandidatesForSecondRound() {
		return namedParameterJdbcTemplate.query(getCandidatesForSecondRoundSql, new RowMapper<Candidate>() {
			public Candidate mapRow(ResultSet resultset, int index) throws SQLException {
				Candidate candidate = new Candidate();
				candidate.setCid(resultset.getInt(1));
				candidate.setCname(resultset.getString(2));
				candidate.setEmail(resultset.getString(3));
				candidate.setTechnologyName(resultset.getString(4));
				return candidate;
			}
		});

	}

	public void changeCandidateStatus(PanelEvaluation panelEvaluation) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("status", 3);
		params.addValue("candidateId", panelEvaluation.getCandidateId());
		namedParameterJdbcTemplate.update(changeCandidateStatusSql, params);
	}

	public Candidate getRoundOfCandidateId(PanelEvaluation panelEvaluation) {
		
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("cid", panelEvaluation.getCandidateId());
		params.addValue("pid", panelEvaluation.getPanelId());
		return namedParameterJdbcTemplate.queryForObject("select round from panel_assignee where cid=:cid and pid=:pid", params, new RowMapper<Candidate>() {
			public Candidate mapRow(ResultSet rs, int arg1) throws SQLException {
				Candidate candidate = new Candidate();
				candidate.setIsVisited(rs.getInt(1));
				return candidate;
			}

		});
	}

}