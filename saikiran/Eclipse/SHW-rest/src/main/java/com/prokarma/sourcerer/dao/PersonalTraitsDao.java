package com.prokarma.sourcerer.dao;

import java.util.List;

import com.prokarma.sourcerer.dto.PersonalTrait;

public interface PersonalTraitsDao {
public List<PersonalTrait> getPersonalTraits();
public boolean addPersonalTrait(PersonalTrait personalTrait);
public boolean editPersonalTrait(PersonalTrait personalTrait);
public boolean deletePersonalTrait(PersonalTrait personalTrait);
}
