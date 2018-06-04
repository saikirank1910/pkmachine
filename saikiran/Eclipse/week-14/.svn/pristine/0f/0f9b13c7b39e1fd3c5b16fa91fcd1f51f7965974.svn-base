package com.pkrm.person.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pkrm.dao.PersonDao;
import com.pkrm.dao.RoleTableDao;
import com.pkrm.dao.UserRoleMapDao;
import com.pkrm.person.Person;
import com.pkrm.person.RoleTable;
import com.pkrm.person.UserRoleMap;

@Component
public class PersonService {
	@Autowired
	private RoleTableDao roleTableDao;

	@Autowired
	private PersonDao personDao;

	@Autowired
	private UserRoleMapDao roleMapDao;

	private int roleId;
	private int userId;

	public int createPerson(Person person) {
		RoleTable roleTable = roleTableDao.getRoleId(person.getRoleName());

		int result = personDao.save(person);

		this.roleId = roleTable.getId();
		this.userId = person.getUserid();
		return result;
	}

	public int insertIntoRole() {
		UserRoleMap roleMap = new UserRoleMap();
		roleMap.setRoleId(this.roleId);
		roleMap.setUserId(this.userId);
		int result = roleMapDao.insert(roleMap);
		return result;
	}

	public int updatePerson(Person person) {
		RoleTable roleTable = roleTableDao.getRoleId(person.getRoleName());

		int result = personDao.edit(person);
		
		this.roleId = roleTable.getId();
		this.userId = person.getUserid();
		return result;
	}

	public int updateRoleTable(Person person) {
		
		roleMapDao.delete(person.getUserid());
		UserRoleMap roleMap = new UserRoleMap();
		roleMap.setRoleId(this.roleId);
		roleMap.setUserId(this.userId);
		int result = roleMapDao.insert(roleMap);
		return result;
	}

	public List<Person> getDetails() {
		return personDao.getDetails();
	}

	public void deletePerson(Person person) {
		roleMapDao.delete(person.getUserid());
		personDao.delete(person.getUserid());
	}

}
