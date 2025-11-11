package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources;

public record TaskResource(Long taskId, Long idPatient, Long idSession, String title, String description, Short status) {
}
