package com.closedsource.psymed.platform.appointmentandadministration.domain.model.queries;

/**
 * Query to get a specific session by ID.
 */
public record GetSessionByIdQuery(Long id) {
    public GetSessionByIdQuery {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Session ID must be greater than 0");
        }
    }
}
