package com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands;

public record UpdateNoteCommand(
        Long sessionId,
        String title,
        String description
) {
}

