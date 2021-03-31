package com.addi.crm.service;

import com.addi.crm.model.Person;

public interface SalesService {

    Person convertLeadIntoProspect(Long nationalIdentificationNumber);
}
