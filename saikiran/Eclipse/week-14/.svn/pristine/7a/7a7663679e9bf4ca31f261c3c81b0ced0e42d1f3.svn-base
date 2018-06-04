package com.pkrm.person.controller;

import java.util.List;
import java.util.function.Consumer;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pkrm.person.Person;
import com.pkrm.person.RoleTable;
import com.pkrm.person.service.PersonService;

@Path("/Person")
public class Controller {
	final static Logger logger = Logger.getLogger(Controller.class);
	
	
	@POST
	@Path("/addPerson")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertPersonDetails(Person person) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("jdbc-context.xml");
		PersonService personService = (PersonService) applicationContext.getBean("personService");
		//System.out.println(person);
		int result1 = 0, result2 = 0;
		try {
			result1=personService.createPerson(person);
		} catch (DataAccessException e) {
			logger.error("error while inserting into person table..!" + e.getMessage());
		}
		try {
			result2=personService.insertIntoRole();
		} catch (DataAccessException e) {
			logger.error("error while inserting into usermaprole table..!" + e.getMessage());
		}
		if (result1 == 0 && result2 == 0)
			return Response.status(500).entity("not inserted").build();
		return Response.status(200).entity("inserted").build();

	}
	@POST
	@Path("/editPerson")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editPersonDetails(Person person) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("jdbc-context.xml");
		PersonService personService = (PersonService) applicationContext.getBean("personService");
		int result1 = 0,result2=0;
		try {
			result1=personService.updatePerson(person);
		} catch (DataAccessException e) {
			logger.error("error while updating into person table..!" + e.getMessage());
		}
		try {
			result2=personService.updateRoleTable(person);
		} catch (DataAccessException e) {
			logger.error("error while updating into rolemap table..!" + e.getMessage());
		}
		if (result1 == 0 && result2==0)
			return Response.status(500).entity("not updated").build();
		return Response.status(200).entity("updated").build();
	}
	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deletePerson(Person person) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("jdbc-context.xml");
		PersonService personService = (PersonService) applicationContext.getBean("personService");
		personService.deletePerson(person);
	}
	
	@GET
	@Path("/getList")
	@Produces("applicatin/Json")
	public Response getDetails() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("jdbc-context.xml");
		PersonService personService = (PersonService) applicationContext.getBean("personService");
		List<Person> result= personService.getDetails();
		if (result != null) {
			return Response.status(200).entity(result).build();
		}
		return Response.status(500).entity("person table is empty").build();
	}
}
