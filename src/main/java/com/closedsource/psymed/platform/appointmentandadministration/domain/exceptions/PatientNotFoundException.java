package com.closedsource.psymed.platform.appointmentandadministration.domain.exceptions;

public class PatientNotFoundException extends RuntimeException {
  public PatientNotFoundException(Long patientId) {
    super("Patient with ID %s not found".formatted(patientId));
  }
}
