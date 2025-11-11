package com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands;

public record DeleteTaskCommand(
        Long sessionId,
        Long taskId
) {
}

