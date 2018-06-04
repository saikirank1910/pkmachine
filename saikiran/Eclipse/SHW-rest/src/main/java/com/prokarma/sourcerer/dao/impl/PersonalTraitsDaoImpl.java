package com.prokarma.sourcerer.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.prokarma.sourcerer.dao.PersonalTraitsDao;
import com.prokarma.sourcerer.dto.PersonalTrait;

@Repository
public class PersonalTraitsDaoImpl implements PersonalTraitsDao {

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private String getPersonalTraitsSql = "select * from PERSONAL_TRAITS";
	private String addPersonalTraitsSql = "insert into PERSONAL_TRAITS values(PERSONAL_TRAITS_ID.nextval,:trait_name)";
	private String editPersonalTraitsSql = "update PERSONAL_TRAITS set PERSONAL_TRAITS=:trait_name where TRAITID=:trait_id";
	private String deletePersonalTraitsSql = "delete from PERSONAL_TRAITS where TRAITID=:trait_id ";
	private String deletePersonalTraitsPreviousEntriesSql = "delete from PERSONAL_TRAITS_RATING where skillid = :trait_id";

	public List<PersonalTrait> getPersonalTraits() {

		return namedParameterJdbcTemplate.query(getPersonalTraitsSql, new RowMapper<PersonalTrait>() {

			public PersonalTrait mapRow(ResultSet resultset, int index) throws SQLException {
				PersonalTrait personalTrait = new PersonalTrait();
				personalTrait.setTraitId(resultset.getInt(1));
				personalTrait.setPersonalTrait(resultset.getString(2));
				return personalTrait;
			}
		});
	}

	public boolean addPersonalTrait(PersonalTrait personalTrait) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("trait_name", personalTrait.getPersonalTrait());
		namedParameterJdbcTemplate.update(addPersonalTraitsSql, params);
		return true;

	}

	public boolean editPersonalTrait(PersonalTrait personalTrait) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("trait_name", personalTrait.getPersonalTrait());
		params.addValue("trait_id", personalTrait.getTraitId());
		namedParameterJdbcTemplate.update(editPersonalTraitsSql, params);
		return true;

	}

	public boolean deletePersonalTrait(PersonalTrait personalTrait) {

		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("trait_id", personalTrait.getTraitId());
		namedParameterJdbcTemplate.update(deletePersonalTraitsSql, params);
		namedParameterJdbcTemplate.update(deletePersonalTraitsPreviousEntriesSql, params);
		return true;

	}

}
