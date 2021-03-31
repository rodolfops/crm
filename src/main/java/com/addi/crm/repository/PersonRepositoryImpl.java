package com.addi.crm.repository;

import com.addi.crm.model.Person;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonRepositoryImpl implements PersonRepository {
    @Override
    public Optional<Person> findByNationalIdentificationNumber(Long nationalIdentificationNumber) {
        return Optional.empty();
    }
}
