package com.addi.crm.client.external;

import com.addi.crm.model.JudicialRecords;
import reactor.core.publisher.Mono;

public interface NationalArchives {

    Mono<JudicialRecords> hasJudicialRecords(Long nationalIdentificationNumber);
}
