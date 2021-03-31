package com.addi.crm.service;

import com.addi.crm.model.Person;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PersonServiceImplTest extends SampleBaseTestCase {

    @InjectMocks
    private PersonServiceImpl personService;

    @Test
    public void equalsPersonsTest(){
        Person person1 = new Person(1l);
        Person person2 = person1;

        boolean theSamePerson = personService.isTheSamePerson( person1, person2 );
        assertTrue(theSamePerson);
    }

    @Test
    public void anotherEqualsPersonsTest(){
        Person person1 = new Person(1l);
        Person person2 = new Person(1l);

        boolean theSamePerson = personService.isTheSamePerson( person1, person2 );
        assertTrue(theSamePerson);
    }

    @Test
    public void notEqualsPersonsTest(){
        Person person1 = new Person(1l);
        Person person2 = new Person(2l);;

        boolean theSamePerson = personService.isTheSamePerson( person1, person2 );
        assertFalse(theSamePerson);
    }
}
