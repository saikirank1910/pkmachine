package com.pkrm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pkrm.person.RoleTable;

@Component
public class RoleTableDao {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	public RoleTable getRoleId(String roleName) {
		String sql = "select roleid from roletable_week12 where rolename=:roleName";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("roleName", roleName);
		return namedParameterJdbcTemplate.queryForObject(sql, params, new RowMapper<RoleTable>() {
			public RoleTable mapRow(ResultSet rs, int rowNum) throws SQLException {
				RoleTable table = new RoleTable();
				table.setId(rs.getInt(1));
				return table;
			}
		});
	}

}