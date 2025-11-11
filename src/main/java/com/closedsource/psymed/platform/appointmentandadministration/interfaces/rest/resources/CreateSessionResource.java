package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources;

import java.util.Date;

/**
 * A resource class representing the data needed to create a new session.
 *
 * @param appointmentDate The date of the appointment. Cannot be null or blank.
 * @param sessionTime    The length of the session in hours. Must be greater than 0.
 *
 * @throws IllegalArgumentException if any of the parameters are invalid.
 */
public record CreateSessionResource(
        Date appointmentDate,
        double sessionTime) {
}
