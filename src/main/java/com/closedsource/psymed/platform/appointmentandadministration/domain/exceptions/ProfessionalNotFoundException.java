package com.closedsource.psymed.platform.appointmentandadministration.domain.exceptions;

public class ProfessionalNotFoundException extends RuntimeException {
  public ProfessionalNotFoundException(Long professionalId) {
    super("Professional with ID %s not found".formatted(professionalId));
  }
}
