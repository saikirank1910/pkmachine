package com.pkrm.event.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pkrm.event.model.EmployeeInformation;

@Repository
public class EmployeeInformationDaoImpl implements EmployeeInformationDao {
	private String saveEmployeeEventDetailsSql = "insert into employee_information(event_Id,employee_Id,position,date_on_duty,time_on_duty,hours_on_duty,"
			+ "previous_rest,direction,last_job_briefing,regulation,Has_been_operaing_longer_than_six_months,suspended)"
			+ "values(:eventId,:employeeId,:position,:dateOnDuty,:timeOnDuty,:hoursOnDuty,:previousRest,:direction,:lastJobBreifing,:regulation,:isHeOperatingLongerThanSixMonths,:suspended)";

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public int saveEmployeeEventDetails(List<EmployeeInformation> list) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		for (EmployeeInformation listOfemployee : list) {
			params.addValue("eventId", listOfemployee.getEventId());
			params.addValue("employeeId", listOfemployee.getEmployeeId());
			params.addValue("position", listOfemployee.getPosition());
			params.addValue("dateOnDuty", listOfemployee.getDateOnDuty());
			params.addValue("timeOnDuty", listOfemployee.getTimeOnDuty().getHours()+":"+listOfemployee.getTimeOnDuty().getMinutes());
			params.addValue("hoursOnDuty", listOfemployee.getHoursOnDuty().getHours()+":"+listOfemployee.getHoursOnDuty().getMinutes());
			params.addValue("previousRest", listOfemployee.getPreviousRest().getHours()+":"+listOfemployee.getPreviousRest().getMinutes());
			params.addValue("direction",listOfemployee.getDirection());
			params.addValue("lastJobBreifing", listOfemployee.getLastJobBreifing());
			params.addValue("regulation",listOfemployee.getRegulation());
			params.addValue("isHeOperatingLongerThanSixMonths", listOfemployee.getIsHeOperatingLongerThanSixMonths());
			params.addValue("suspended", listOfemployee.getSuspended());
			namedParameterJdbcTemplate.update(saveEmployeeEventDetailsSql, params);
		}
		return 1;
	}

}
