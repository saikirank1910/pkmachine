package com.pkrm.event.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pkrm.event.model.Train;


@Repository
public class TrainDaoImpl implements TrainDao {
	
	private String getLikeTrainDetailsSql="SELECT t_id,train_section,train_id,train_date FROM train where Train_Id like :trainName";

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public List<Train> getLikeTrainDetails(String trainName) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("trainName","%"+trainName+"%");
		return namedParameterJdbcTemplate.query(getLikeTrainDetailsSql,params, new RowMapper<Train>() {
			public Train mapRow(ResultSet resultSet, int index) throws SQLException {
				Train train = new Train();
			    train.settId(resultSet.getInt(1));
			    train.setTrainSection(resultSet.getInt(2));
			    train.setTrainId(resultSet.getString(3));
			    train.setTrainDate(resultSet.getDate(4));
				return train;
			}

		});
	}

}
