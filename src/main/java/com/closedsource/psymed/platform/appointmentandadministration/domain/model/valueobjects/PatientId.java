package com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record PatientId(Long patientId) {

    /**
     * Compact constructor with validation to ensure a valid Patient ID.
     *
     * @param patientId The unique identifier of the patient.
     *                  It must be a positive number.
     * @throws IllegalArgumentException if the ID is null or less than 0.
     */
    public PatientId {
        if (patientId == null || patientId < 0) {
            throw new IllegalArgumentException("Patient ID cannot be null or negative.");
        }
    }

    /**
     * Default constructor.
     * Initializes the Patient ID to 0.
     */
    public PatientId() {
        this(0L);
    }
}
