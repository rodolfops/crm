package com.addi.crm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private Long identificationNumber;
    private LocalDate birthDate;
    private String firstName;
    private String lastName;
    private String email;
    private String country;

    public Person(Long identificationNumber){
        this.identificationNumber = identificationNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals( identificationNumber, person.identificationNumber ) && Objects.equals( birthDate, person.birthDate ) && Objects.equals( firstName, person.firstName ) && Objects.equals( lastName, person.lastName ) && Objects.equals( email, person.email ) && Objects.equals( country, person.country );
    }
}
