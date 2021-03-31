package com.addi.crm.service;

import com.addi.crm.client.external.*;
import com.addi.crm.client.internal.ProspectQualificationSystem;
import com.addi.crm.model.JudicialRecords;
import com.addi.crm.model.Person;
import com.addi.crm.model.PersonWithJudicialRecords;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Objects;

@Service
@Slf4j
public class SalesServiceImpl implements SalesService {

    private NationalArchives nationalArchives;
    private NationalRegistrySystem nationalRegistrySystem;
    private ProspectQualificationSystem prospectQualificationSystem;
    private PersonService personService;

    @Autowired
    public SalesServiceImpl(final NationalArchives nationalArchives,
                            final NationalRegistrySystem nationalRegistrySystem,
                            final ProspectQualificationSystem prospectQualificationSystem,
                            final PersonService personService) {
        this.nationalArchives = nationalArchives;
        this.nationalRegistrySystem = nationalRegistrySystem;
        this.prospectQualificationSystem = prospectQualificationSystem;
        this.personService = personService;
    }

    public Person convertLeadIntoProspect(Long nationalIdentificationNumber) {

        Mono<Person> personMono = this.nationalRegistrySystem.findByNationalIdentificationNumber(nationalIdentificationNumber).subscribeOn( Schedulers.boundedElastic());
        Mono<JudicialRecords> judicialRecordsMono = this.nationalArchives.hasJudicialRecords(nationalIdentificationNumber).subscribeOn(Schedulers.boundedElastic());
        Mono<PersonWithJudicialRecords> personWithJudicialRecordsMono = Mono.zip( personMono, judicialRecordsMono, PersonWithJudicialRecords::new );

        Person person = personService.findByNationalIdentificationNumber( nationalIdentificationNumber )
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Person not found"));

        if(Objects.isNull(personMono.block())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Person not found in National Records");
        }

        if(personService.isTheSamePerson(personWithJudicialRecordsMono.block().getPerson(),person)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Person different from database");
        }
        if(personWithJudicialRecordsMono.block().getRecords().hasRecords()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Person has judicial records");
        }

        int satisfactoryScore = this.prospectQualificationSystem.getSatisfactoryScore(nationalIdentificationNumber);
        if(satisfactoryScore < 60){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unsatisfying score");
        }
        return personService.convertLeadToProspect(nationalIdentificationNumber);
    }
}
