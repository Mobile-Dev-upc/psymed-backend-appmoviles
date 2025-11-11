package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.CreateSessionCommand;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.AppointmentDate;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.PatientId;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.ProfessionalId;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.SessionTime;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.CreateSessionResource;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A utility class that converts a {@link CreateSessionResource} object into a {@link CreateSessionCommand} object.
 */
public class CreateSessionCommandFromResourceAssembler {
    public static CreateSessionCommand toCommandFromResource(Long patientId, Long professionalId, CreateSessionResource resource) {
        return new CreateSessionCommand(patientId, professionalId, resource.appointmentDate(), resource.sessionTime());
    }
}
