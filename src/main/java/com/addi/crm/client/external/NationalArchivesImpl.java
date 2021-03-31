package com.addi.crm.client.external;

import com.addi.crm.model.JudicialRecords;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class NationalArchivesImpl implements NationalArchives {
    @Override
    public Mono<JudicialRecords> hasJudicialRecords(Long nationalIdentificationNumber) {
        return Mono.empty();
    }
}
