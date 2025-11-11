package com.closedsource.psymed.platform.patientreport.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
public record PatientId(Long patientId) {
    public PatientId {
        if(patientId < 0) {
            throw new IllegalArgumentException("PatientId must be greater than 0");
        }
    }

    public PatientId() {
        this(0L);
    }
}
