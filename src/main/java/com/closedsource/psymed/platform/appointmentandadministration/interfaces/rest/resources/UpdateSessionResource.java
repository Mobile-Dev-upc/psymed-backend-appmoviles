package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources;

import java.util.Date;

/**
 * Resource used to update an existing session (medical appointment).
 *
 * @param appointmentDate The new appointment date.
 * @param sessionTime     The duration of the session in hours.
 */
public record UpdateSessionResource(
        Date appointmentDate,
        double sessionTime
) {
    public UpdateSessionResource {
        if (appointmentDate == null) {
            throw new IllegalArgumentException("Appointment date cannot be null");
        }
        if (sessionTime <= 0) {
            throw new IllegalArgumentException("Session time must be greater than 0");
        }
    }
}

