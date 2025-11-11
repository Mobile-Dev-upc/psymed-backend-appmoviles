package com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands;

public record UpdateTaskCommand(
        Long sessionId,
        Long taskId,
        String title,
        String description
) {
}

