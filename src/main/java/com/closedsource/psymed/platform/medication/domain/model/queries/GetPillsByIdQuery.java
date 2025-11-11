package com.closedsource.psymed.platform.medication.domain.model.queries;

public record GetPillsByIdQuery(Long medicationId) {
    public GetPillsByIdQuery {
        if (medicationId == null || medicationId <= 0) throw new IllegalArgumentException("Medication ClinicalHistoryId cannot be 0 or less");
    }
}
