package com.pkrm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.pkrm.person.RoleTable;

public class RoleTableDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public RoleTable getRoleId(String roleName) {
		String sql = "select roleid from roletable where rolename=:roleName";
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
