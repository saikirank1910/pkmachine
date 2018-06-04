package com.pkrm.person.controller;

import java.util.function.Consumer;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
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
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	@POST
	@Path("/addPerson")
	public Response insertPersonDetails(@DefaultValue("0") @FormParam("id") int id,
			@FormParam("userName") String userName, @FormParam("firstName") String firstName,
			@FormParam("lastName") String lastName, @FormParam("role") String roleName) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("jdbc-context.xml");
		PersonService personService = (PersonService) applicationContext.getBean("personService");
		Person person = new Person();
		RoleTable roleTable = new RoleTable();
		person.setRole(roleTable);
		person.setUserid(id);
		person.setUserName(userName);
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.getRole().setName(roleName);
		System.out.println(person);
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
	@PUT
	@Path("/editPerson")
	public Response editPersonDetails(@DefaultValue("0") @FormParam("id") int id,
			@FormParam("userName") String userName, @FormParam("firstName") String firstName,
			@FormParam("lastName") String lastName, @FormParam("role") String roleName) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("jdbc-context.xml");
		PersonService personService = (PersonService) applicationContext.getBean("personService");
		Person person = new Person();
		RoleTable roleTable = new RoleTable();
		person.setRole(roleTable);
		person.setUserid(id);
		person.setUserName(userName);
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.getRole().setName(roleName);
		System.out.println(person);
		int result1 = 0, result2 = 0;
		try {
			result1=personService.updatePerson(person);
		} catch (DataAccessException e) {
			logger.error("error while updating into person table..!" + e.getMessage());
		}
		if (result1 == 0)
			return Response.status(500).entity("not updated").build();
		return Response.status(200).entity("updated").build();
	}
	/*@DELETE
	@Path("/delete")
	public Response deletePerson() {
		return 
	}*/
}
