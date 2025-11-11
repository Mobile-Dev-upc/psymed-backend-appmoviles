package com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands;

import java.util.Date;

public record UpdateSessionCommand(
        Long sessionId,
        Long patientId,
        Long professionalId,
        Date appointmentDate,
        double sessionTime
) {
}

