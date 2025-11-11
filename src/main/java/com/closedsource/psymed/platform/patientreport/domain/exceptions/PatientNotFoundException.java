package com.closedsource.psymed.platform.patientreport.domain.exceptions;

public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException(Long patientId) {
        super("Patient with ID %s not found".formatted(patientId));
    }
}
