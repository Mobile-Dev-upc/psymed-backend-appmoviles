package com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands;

public record AddTaskToSessionCommand(Long sessionId, String title, String description) {
}
