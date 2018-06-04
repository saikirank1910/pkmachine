package com.pkrm.person.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.pkrm.dao.PersonDao;
import com.pkrm.dao.RoleTableDao;
import com.pkrm.dao.UserRoleMapDao;
import com.pkrm.person.Person;
import com.pkrm.person.RoleTable;
import com.pkrm.person.UserRoleMap;

public class PersonService {
	@Autowired
	private RoleTableDao roleTableDao;

	@Autowired
	private PersonDao personDao;

	@Autowired
	private UserRoleMapDao roleMapDao;

	private int roleId;
	private int userId;

	public void createPerson(Person person) {
		RoleTable roleTable = roleTableDao.getRoleId(person.getRole().getName());

		personDao.save(person);

		this.roleId = roleTable.getId();
		this.userId = person.getUserid();
	}

	public void insertIntoRole() {
		UserRoleMap roleMap = new UserRoleMap();
		roleMap.setRoleId(this.roleId);
		roleMap.setUserId(this.userId);
		roleMapDao.insert(roleMap);
	}

	/*public void editPerson(Person person) {
		personDao.edit(person);
	}

	public void deletePerson(int id) {
		roleMapDao.delete(id);
		personDao.delete(id);
	}*/
}
