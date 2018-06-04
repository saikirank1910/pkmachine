package com.pkrm.event.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pkrm.event.model.Region;


@Repository
public class RegionDaoImpl implements RegionDao{
	private String getAllRegionsSql="SELECT region_id,region_name FROM region";
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	public List<Region> getAllRegions() {
		return namedParameterJdbcTemplate.query(getAllRegionsSql, new RowMapper<Region>() {
			public Region mapRow(ResultSet resultSet, int index) throws SQLException {
				Region region = new Region();
				region.setRegionId(resultSet.getInt(1));
				region.setRegionName(resultSet.getString(2));
				return region;
			}

		});
	}
}
