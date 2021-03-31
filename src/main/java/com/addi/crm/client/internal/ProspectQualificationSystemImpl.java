package com.addi.crm.client.internal;

import org.springframework.stereotype.Component;

@Component
public class ProspectQualificationSystemImpl implements ProspectQualificationSystem {
    @Override
    public int getSatisfactoryScore(Long identificationNumber) {
        return 0;
    }
}
