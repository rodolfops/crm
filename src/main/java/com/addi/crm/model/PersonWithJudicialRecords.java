package com.addi.crm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PersonWithJudicialRecords {
    private Person person;
    private JudicialRecords records;
}
