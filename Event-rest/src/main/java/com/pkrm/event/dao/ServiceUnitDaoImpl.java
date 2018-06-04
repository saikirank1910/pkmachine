package com.pkrm.event.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pkrm.event.model.ServiceUnit;


@Repository
public class ServiceUnitDaoImpl implements ServiceUnitDao{
	private String getAllServiceUnitsSql="SELECT Service_unit_Id,Service_unit_name FROM service_unit";
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public List<ServiceUnit> getAllServiceUnits() {
		return namedParameterJdbcTemplate.query(getAllServiceUnitsSql, new RowMapper<ServiceUnit>() {
			public ServiceUnit mapRow(ResultSet resultSet, int index) throws SQLException {
				ServiceUnit serviceUnit = new ServiceUnit();
				serviceUnit.setServiceUnitId(resultSet.getInt(1));
				serviceUnit.setServiceUnitName(resultSet.getString(2));
				return serviceUnit;
			}

		});
	}

}
