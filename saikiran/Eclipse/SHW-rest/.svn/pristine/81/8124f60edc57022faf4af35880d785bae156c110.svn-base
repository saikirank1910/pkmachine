package com.prokarma.sourcerer.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.prokarma.sourcerer.dao.SkillLevelDao;
import com.prokarma.sourcerer.dto.SkillLevel;

@Component
public class SkillLevelDaoImpl implements SkillLevelDao {
	private String getSkillLevelsSql = "select * from skill_level";
	private String addSkillLevelSql = "insert into skill_level values(skilllevelid.nextval,:skilllevel)";
	private String editSkillLevelSql = "update skill_level set skillname = :skilllevel  where SKILLLEVELID= :id";
	private String deleteSkillLevelSql = "delete from skill_level  where SKILLLEVELID= :id";
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<SkillLevel> getSkillLevels() {
	return namedParameterJdbcTemplate.query(getSkillLevelsSql, new RowMapper<SkillLevel>() {

			public SkillLevel mapRow(ResultSet resultset, int index) throws SQLException {
				SkillLevel skillLevel = new SkillLevel();
				skillLevel.setSkillId(resultset.getInt(1));
				skillLevel.setSkillLevel(resultset.getString(2));
				return skillLevel;
			}

		});

	}

	public boolean addSkillLevel(SkillLevel skillLevel) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("skilllevel", skillLevel.getSkillLevel());
		namedParameterJdbcTemplate.update(addSkillLevelSql, params);

			return true;

	}

	public boolean editSkillLevel(SkillLevel skillLevel) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("skilllevel", skillLevel.getSkillLevel());
		params.addValue("id", skillLevel.getSkillId());
		namedParameterJdbcTemplate.update(editSkillLevelSql, params);

			return true;
	}

	public boolean deleteSkillLevel(int id) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		namedParameterJdbcTemplate.update(deleteSkillLevelSql, params);

			return true;
	}

}
