package com.addi.crm.client.external;

import com.addi.crm.model.Person;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class NationalRegistrySystemImpl implements NationalRegistrySystem {
    @Override
    public Mono<Person> findByNationalIdentificationNumber(Long identificationNumber) {
        return Mono.empty();
    }
}
