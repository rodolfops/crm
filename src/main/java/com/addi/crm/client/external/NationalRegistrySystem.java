package com.addi.crm.client.external;

import com.addi.crm.model.Person;
import reactor.core.publisher.Mono;

public interface NationalRegistrySystem {

    Mono<Person> findByNationalIdentificationNumber(Long identificationNumber);
}
