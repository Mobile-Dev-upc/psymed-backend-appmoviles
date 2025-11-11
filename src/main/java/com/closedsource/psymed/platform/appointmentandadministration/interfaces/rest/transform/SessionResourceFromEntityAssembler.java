package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.aggregates.Session;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.SessionResource;

/**
 * A utility class for converting a {@link Session} entity into a {@link SessionResource}.
 */
public class SessionResourceFromEntityAssembler {

    public static SessionResource toResourceFromEntity(Session entity) {
        return new SessionResource(
                entity.getId(),
                entity.getPatientId().patientId(),
                entity.getProfessionalId().professionalId(),
                entity.getAppointmentDate().getAppointmentDate(),
                entity.getSessionTime().getDurationInHours()
        );
    }
}
