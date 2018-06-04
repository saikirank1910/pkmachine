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

import com.prokarma.sourcerer.dao.CandidateSelfRatingDao;
import com.prokarma.sourcerer.dto.CandidateSelfRating;

@Component
public class CandidateSelfRatingDaoImpl implements CandidateSelfRatingDao {
	private String insertSelfRatingSql = "insert into candidate_self_rating values (csrid.nextval,:ratingId,:technolgyId,:cid)";
	private String getSelfRatingSql = "select c.RATINGID,t.TECHNICLESUBSKILL from candidate_self_rating c,techinicle_sub_skills t where c.technicleid=t.tsskillid and c.cid = :cid";

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public Boolean insertSelfRating(List<CandidateSelfRating> list) {

		SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(list.toArray());
			namedParameterJdbcTemplate.batchUpdate(insertSelfRatingSql, batch);
	
		return true;
	}

	public List<CandidateSelfRating> getSelfRating(int cid) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("cid", cid);
		return namedParameterJdbcTemplate.query(getSelfRatingSql, params, new RowMapper<CandidateSelfRating>() {
			public CandidateSelfRating mapRow(ResultSet resultSet, int index) throws SQLException {
				final CandidateSelfRating candidateSelfRating = new CandidateSelfRating();

				candidateSelfRating.setRatingId(resultSet.getInt(1));
				candidateSelfRating.setTechnologyName(resultSet.getString(2));
				return candidateSelfRating;
			}

		});

	}

}
