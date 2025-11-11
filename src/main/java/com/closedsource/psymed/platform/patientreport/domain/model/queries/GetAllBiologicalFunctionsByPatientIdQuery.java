package com.closedsource.psymed.platform.patientreport.domain.model.queries;

import com.closedsource.psymed.platform.patientreport.domain.model.valueobjects.PatientId;

public record GetAllBiologicalFunctionsByPatientIdQuery(PatientId patientId) {
    public GetAllBiologicalFunctionsByPatientIdQuery {
        if(patientId.patientId() < 0) {
            throw new IllegalArgumentException("PatientId must be greater than 0");
        }
    }
}
