package com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ProfessionalId(Long professionalId) {

    /**
     * Compact constructor with validation to ensure a valid Professional ID.
     *
     * @param professionalId The unique identifier of the professional.
     *                       It must be a positive number.
     * @throws IllegalArgumentException if the ID is null or less than 0.
     */
    public ProfessionalId {
        if (professionalId == null || professionalId < 0) {
            throw new IllegalArgumentException("Professional ID cannot be null or negative.");
        }
    }

    /**
     * Default constructor.
     * Initializes the Professional ID to 0.
     */
    public ProfessionalId() {
        this(0L);
    }
}
