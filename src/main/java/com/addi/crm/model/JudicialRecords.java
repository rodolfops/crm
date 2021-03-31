package com.addi.crm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class JudicialRecords {
    @Setter
    private boolean hasRecords;

    @Getter
    @Setter
    private List<String> records;

    public boolean hasRecords(){
        return hasRecords;
    }

    public JudicialRecords(boolean hasRecords) {
        this.hasRecords = hasRecords;
    }
}
