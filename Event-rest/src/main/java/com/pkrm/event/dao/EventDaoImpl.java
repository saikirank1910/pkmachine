package com.pkrm.event.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.pkrm.event.model.Event;

@Repository
public class EventDaoImpl implements EventDao {

	private String insertIntoEvent = "insert into event_information(type_of_event,event_date,date_of_knowledge,result_of_event,is_drug_and_alcohol,"
			+ "event_time,time_zone,crew_description_of_incident,manager_comments,t_id,loads,lead_locomotive,empties,tonnage,length,max_authorized_speed,"
			+ "speed_at_time_of_incident,subdivision_name,mile_post,region_name,service_unit_name,signal_territory,type_of_track,type_of_authority,rail_road,"
			+ "within_10_miles_of_terminal,is_initial_or_final,nearest_station,crew_type,operation,weather,visibility,grade,curvature,ftx_event,technology,"
			+ "blue_flag,ptc_engaged,created_by,created_date,updated_by,updated_date,history_flag) "
			+ "values(:typeOfevent,:eventDate,:dateOfKnowledge,:resultOfEvent,:isDrugAndAlcohol,:eventTime,:timeZone,:crewDescriptionOfIncident,:managerComments,:trainId,:loads,"
			+ ":leadLocomotive,:empties,:tonnage,:length,:maxAuthorizedSpeed,:speedAtTimeOfIncident,:subDivisionName,:milePost,:regionName,"
			+ ":serviceUnitName,:signalTerritory,:typeOfTrack,:typeOfAuthority,:railRoad,:withinTenMilesOFTerminal,:isInitialOrFinal,:nearestStation,:crewType,:operation,:weather,"
			+ ":visibility,:grade,:curvature,:ftxEvent,:technology,:blueFalg,:ptcEngaged,:createdBy,:createdDate,:updatedBy,:updatedDate,:historyFlag)";

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public int saveEvent(Event event) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("typeOfevent",event.getTypeOfEvent());
		params.addValue("eventDate",event.getEventDate());
		params.addValue("dateOfKnowledge",event.getEventOfKnowledge());
		params.addValue("resultOfEvent",event.getResultOfEvent());
		params.addValue("isDrugAndAlcohol",event.getIsDrugOrAlcohol());
		params.addValue("eventTime",event.getEventTime().getHours()+":"+event.getEventTime().getMinutes());
		params.addValue("timeZone",event.getTimeZone());
		params.addValue("crewDescriptionOfIncident",event.getCrewDescriptionOfIncident());
		params.addValue("managerComments",event.getManagerComments());
		params.addValue("trainId",event.getTrainId());
		params.addValue("loads",event.getLoads());
		params.addValue("leadLocomotive",event.getLocomotiveId());
		params.addValue("empties",event.getEmpties());
		params.addValue("tonnage",event.getTonnage());
		params.addValue("length",event.getLength());
		params.addValue("maxAuthorizedSpeed",event.getMaxAuthorizedSpeed());
		params.addValue("speedAtTimeOfIncident",event.getSpeedAtTheTimeOfIncident());
		params.addValue("subDivisionName",event.getSubDivisionName());
		params.addValue("milePost",event.getMilePost());
		params.addValue("regionName",event.getRegionName());
		params.addValue("serviceUnitName",event.getServiceUnitName());
		params.addValue("signalTerritory",event.getSignalTerritory());
		params.addValue("typeOfTrack",event.getTypeOfTrack());
		params.addValue("typeOfAuthority",event.getTypeOfAuthority());
		params.addValue("railRoad",event.getRailRoad());
		params.addValue("withinTenMilesOFTerminal",event.getWithinTenMilesOfTerminal());
		params.addValue("isInitialOrFinal",event.getIsInitialOrFinal());
		params.addValue("nearestStation",event.getNearestStation());
		params.addValue("crewType",event.getCrewType());
		params.addValue("operation",event.getCrewType());
		params.addValue("weather",event.getWeather());
		params.addValue("visibility",event.getVisibility());
		params.addValue("grade",event.getGrade());
		params.addValue("curvature",event.getCurvature());
		params.addValue("ftxEvent",event.getFtxEvent());
		params.addValue("technology",event.getTechnology());
		params.addValue("blueFalg",event.getBlueFlag());
		params.addValue("ptcEngaged",event.getPtcEngaged());
		params.addValue("createdBy",event.getCreatedBy());
		params.addValue("createdDate",event.getCreatedDate());
		params.addValue("updatedBy",event.getUpdatedBy());
		params.addValue("updatedDate",event.getUpdatedDate());
		params.addValue("historyFlag",event.getHistoryFlag());
		final KeyHolder holder = new GeneratedKeyHolder();
		int result = namedParameterJdbcTemplate.update(insertIntoEvent, params,holder);
	    Number generatedId = holder.getKey();
		if(result==1) {
			return generatedId.intValue();
		}
		return 0;
	}

}
