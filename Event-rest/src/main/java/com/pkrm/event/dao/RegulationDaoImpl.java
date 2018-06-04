package com.pkrm.event.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pkrm.event.model.Regulation;


@Repository
public class RegulationDaoImpl implements RegulationDao{
	private String getAllServiceUnitsSql="SELECT Regulation_Id,Regulation_name FROM regulation_table";
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public List<Regulation> getAllRegulationDetails() {
		return namedParameterJdbcTemplate.query(getAllServiceUnitsSql, new RowMapper<Regulation>() {
			public Regulation mapRow(ResultSet resultSet, int index) throws SQLException {
				Regulation regulation = new Regulation();
				regulation.setRegulationId(resultSet.getInt(1));
				regulation.setRegulationName(resultSet.getString(2));
				return regulation;
			}

		});
	}

}
