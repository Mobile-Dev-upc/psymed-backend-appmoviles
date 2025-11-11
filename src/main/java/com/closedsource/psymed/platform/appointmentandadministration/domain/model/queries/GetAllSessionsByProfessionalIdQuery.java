package com.closedsource.psymed.platform.appointmentandadministration.domain.model.queries;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.ProfessionalId;

/**
 * Query to get all appointments for a specific professional.
 * @param professionalId The unique ID of the professional.
 */
public record GetAllSessionsByProfessionalIdQuery(String professionalId) {
    public GetAllSessionsByProfessionalIdQuery {
        if (professionalId == null) {
            throw new IllegalArgumentException("professionalId cannot be null or less than or equal to 0");
        }
    }
}
