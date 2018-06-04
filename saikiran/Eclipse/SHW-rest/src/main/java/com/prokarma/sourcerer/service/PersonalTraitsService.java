package com.prokarma.sourcerer.service;

import java.util.List;

import com.prokarma.sourcerer.dto.PersonalTrait;

public interface PersonalTraitsService {
public List<PersonalTrait> getPersonalTraits();
public boolean addTrait(PersonalTrait personalTrait);
public boolean editTrait(PersonalTrait personalTrait);
public boolean deleteTrait(PersonalTrait personalTrait);

}
