package com.pkrm.event.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pkrm.event.model.SubDivision;


@Repository
public class SubDivisionDaoImpl implements SubDivisionDao{
	
	private String getSubdivisionSql="SELECT sub_division_id,sub_division_name FROM sub_division";
	private String getMilePostAndRegionSql ="select sd.mile_post,r.region_name,su.service_unit_name from sub_division sd,region r,service_unit su where sub_division_name=:subDivisionName and sd.Service_unit_Id=su.Service_unit_Id and r.Region_Id=su.Region_Id";
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<SubDivision> getAllSubDivisionNames() {
		return namedParameterJdbcTemplate.query(getSubdivisionSql, new RowMapper<SubDivision>() {
			public SubDivision mapRow(ResultSet resultSet, int index) throws SQLException {
			    SubDivision subdivision = new SubDivision();
			    subdivision.setSubDivisionId(resultSet.getInt(1));
			    subdivision.setSubDivisionName(resultSet.getString(2));
				return subdivision;
			}

		});
	}

	public SubDivision getMilePostAndRegion(final SubDivision subDivision) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("subDivisionName", subDivision.getSubDivisionName());
		return namedParameterJdbcTemplate.queryForObject(getMilePostAndRegionSql, params, new RowMapper<SubDivision>() {
			public SubDivision mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				SubDivision subdivisionFromDB = new SubDivision();
				subdivisionFromDB.setMilePost(resultSet.getInt(1));   
				subdivisionFromDB.setRegionName(resultSet.getString(2));
				subdivisionFromDB.setServiceUnitName(resultSet.getString(3));
				return subdivisionFromDB;
			}

		});
	}

}
