package com.addi.crm.controller;

import com.addi.crm.model.Person;
import com.addi.crm.service.SalesService;
import com.addi.crm.service.SalesServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales")
final class SalesController {

    private final SalesService salesPipelineService;

    public SalesController(SalesService salesPipelineService) {
        this.salesPipelineService = salesPipelineService;
    }

    @PatchMapping("/convert/{nationalIdentificationNumber}")
    public ResponseEntity<Person> transformLeadIntoProspect(@PathVariable String nationalIdentificationNumber){
        return ResponseEntity.ok().body(salesPipelineService.convertLeadIntoProspect(Long.valueOf(nationalIdentificationNumber)));
    }
}
