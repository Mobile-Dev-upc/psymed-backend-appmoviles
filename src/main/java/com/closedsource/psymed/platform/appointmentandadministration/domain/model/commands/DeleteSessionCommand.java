package com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands;

public record DeleteSessionCommand(
        Long sessionId,
        Long patientId,
        Long professionalId
) {
}

