package com.closedsource.psymed.platform.appointmentandadministration.domain.model.queries;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.PatientId;

/**
 * Query to get all appointments for a specific patient.
 * @param patientId The unique ID of the patient.
 */
public record GetAllSessionsByPatientIdQuery(String patientId) {
    public GetAllSessionsByPatientIdQuery {
        if (patientId == null) {
            throw new IllegalArgumentException("patientId cannot be null or empty");
        }
    }
}