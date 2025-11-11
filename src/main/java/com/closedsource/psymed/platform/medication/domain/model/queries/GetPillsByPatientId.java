package com.closedsource.psymed.platform.medication.domain.model.queries;

public record GetPillsByPatientId(Long patientId) {
    public GetPillsByPatientId {
        if (patientId == null || patientId <= 0) throw new IllegalArgumentException("Medication's patient id cannot be 0 or less");
    }
}
