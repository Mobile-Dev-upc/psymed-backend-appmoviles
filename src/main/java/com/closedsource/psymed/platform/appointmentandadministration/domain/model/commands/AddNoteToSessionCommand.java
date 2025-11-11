package com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands;

public record AddNoteToSessionCommand(Long sessionId ,String title, String description) {
}
