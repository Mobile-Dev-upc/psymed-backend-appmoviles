package com.closedsource.psymed.platform.appointmentandadministration.application.internal.outboundservices;

public interface AppointmentVersionOfExternalProfileService {
    public boolean existsPatientById(Long patientId);
    public boolean existsProfessionalById(Long professionalId);
}
