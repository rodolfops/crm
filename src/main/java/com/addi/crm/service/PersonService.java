package com.addi.crm.service;

import com.addi.crm.model.Person;

import java.util.Optional;

public interface PersonService {

    default boolean isTheSamePerson(Person person1, Person person2) {
        return person1.equals( person2 );
    }

    Optional<Person> findByNationalIdentificationNumber(Long identificationNumber);

    Person convertLeadToProspect(Long nationalIdentificationNumber);
}
