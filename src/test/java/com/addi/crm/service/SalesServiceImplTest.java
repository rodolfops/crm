package com.addi.crm.service;

import com.addi.crm.client.external.NationalArchives;
import com.addi.crm.client.external.NationalRegistrySystem;
import com.addi.crm.client.internal.ProspectQualificationSystem;
import com.addi.crm.model.JudicialRecords;
import com.addi.crm.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class SalesServiceImplTest{

    @InjectMocks
    private SalesServiceImpl salesServiceImpl;

    @Mock
    private NationalArchives nationalArchives;
    @Mock
    private NationalRegistrySystem nationalRegistrySystem;
    @Mock
    private ProspectQualificationSystem prospectQualificationSystem;
    @Mock
    private PersonService personService;

    @Test
    public void convertLeadIntoProspectTest(){
        Person monoPerson = new Person(1l);
        JudicialRecords judicialRecordsMono = new JudicialRecords(false);
        Mockito.when(nationalRegistrySystem.findByNationalIdentificationNumber( 1l )).thenReturn( Mono.just( monoPerson ));
        Mockito.when(nationalArchives.hasJudicialRecords(1l)).thenReturn( Mono.just( judicialRecordsMono ) );
        Mockito.when(personService.findByNationalIdentificationNumber(1l)).thenReturn( Optional.of(monoPerson));
        Mockito.when(prospectQualificationSystem.getSatisfactoryScore(1l)).thenReturn(61);
        Mockito.when(personService.convertLeadToProspect(1l)).thenReturn(monoPerson);

        Person returnedPerson = salesServiceImpl.convertLeadIntoProspect(1l);

        assertThat(returnedPerson).isEqualTo(monoPerson);
    }

    @Test(expected = ResponseStatusException.class)
    public void unsatisfyingLeadScoreTest(){
        Person monoPerson = new Person(1l);
        JudicialRecords judicialRecordsMono = new JudicialRecords(false);
        Mockito.when(nationalRegistrySystem.findByNationalIdentificationNumber( 1l )).thenReturn( Mono.just( monoPerson ));
        Mockito.when(nationalArchives.hasJudicialRecords(1l)).thenReturn( Mono.just( judicialRecordsMono ) );
        Mockito.when(personService.findByNationalIdentificationNumber(1l)).thenReturn( Optional.of(monoPerson));
        Mockito.when(prospectQualificationSystem.getSatisfactoryScore(1l)).thenReturn(59);

        salesServiceImpl.convertLeadIntoProspect(1l);
    }

    @Test(expected = ResponseStatusException.class)
    public void hasJudicialRecordsTest(){
        Person monoPerson = new Person(1l);
        JudicialRecords judicialRecordsMono = new JudicialRecords(true);
        Mockito.when(nationalRegistrySystem.findByNationalIdentificationNumber( 1l )).thenReturn( Mono.just( monoPerson ));
        Mockito.when(nationalArchives.hasJudicialRecords(1l)).thenReturn( Mono.just( judicialRecordsMono ) );

        salesServiceImpl.convertLeadIntoProspect(1l);
    }

    @Test(expected = ResponseStatusException.class)
    public void isNotTheSamePersonTest(){
        Person monoPerson = new Person(1l);
        Person monoPerson2 = new Person(2l);
        JudicialRecords judicialRecordsMono = new JudicialRecords(true);
        Mockito.when(nationalRegistrySystem.findByNationalIdentificationNumber( 1l )).thenReturn( Mono.just( monoPerson ));
        Mockito.when(nationalArchives.hasJudicialRecords(1l)).thenReturn( Mono.just( judicialRecordsMono ) );
        Mockito.when(personService.findByNationalIdentificationNumber(1l)).thenReturn( Optional.of(monoPerson2));

        salesServiceImpl.convertLeadIntoProspect(1l);
    }

    @Test(expected = ResponseStatusException.class)
    public void personNotFoundInRepositoryTest(){
        Person monoPerson = new Person(1l);
        JudicialRecords judicialRecordsMono = new JudicialRecords(true);
        Mockito.when(nationalRegistrySystem.findByNationalIdentificationNumber( 1l )).thenReturn( Mono.just( monoPerson ));
        Mockito.when(nationalArchives.hasJudicialRecords(1l)).thenReturn( Mono.just( judicialRecordsMono ) );
        Mockito.when(personService.findByNationalIdentificationNumber(1l)).thenReturn( Optional.empty());

        salesServiceImpl.convertLeadIntoProspect(1l);
    }

    @Test(expected = ResponseStatusException.class)
    public void personNotFoundTest(){
        Person monoPerson = new Person(1l);
        JudicialRecords judicialRecordsMono = new JudicialRecords(true);
        Mockito.when(nationalRegistrySystem.findByNationalIdentificationNumber( 1l )).thenReturn( Mono.empty());
        Mockito.when(nationalArchives.hasJudicialRecords(1l)).thenReturn( Mono.just( judicialRecordsMono ) );
        Mockito.when(personService.findByNationalIdentificationNumber(1l)).thenReturn( Optional.of( monoPerson ));

        salesServiceImpl.convertLeadIntoProspect(1l);
    }
}