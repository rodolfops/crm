package com.addi.crm.service;

import com.addi.crm.model.Person;
import com.addi.crm.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Optional<Person> findByNationalIdentificationNumber(Long identificationNumber) {
        return personRepository.findByNationalIdentificationNumber(identificationNumber);
    }

    @Override
    public Person convertLeadToProspect(Long nationalIdentificationNumber) {
        //in this method the person will be moved from the sales lead stage into the prospect stage of the sales pipeline.
        return new Person(1l);
    }
}
