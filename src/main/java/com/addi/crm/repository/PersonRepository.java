package com.addi.crm.repository;

import com.addi.crm.model.Person;

import java.util.Optional;

public interface PersonRepository {

    Optional<Person> findByNationalIdentificationNumber(Long nationalIdentificationNumber);
}
