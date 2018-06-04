package com.pkrm.person.service;

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
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public int createPerson(Person person) {
		RoleTable roleTable = roleTableDao.getRoleId(person.getRole().getName());

		int result = personDao.save(person);

		this.roleId = roleTable.getId();
		this.userId = person.getUserid();
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public int insertIntoRole() {
		UserRoleMap roleMap = new UserRoleMap();
		roleMap.setRoleId(this.roleId);
		roleMap.setUserId(this.userId);
		int result = roleMapDao.insert(roleMap);
		return result;
	}
	public int updatePerson(Person person) {
		return personDao.edit(person);
	}

}
