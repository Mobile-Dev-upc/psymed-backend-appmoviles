package com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands;

public record UpdateTaskStatusToCompleteCommand(Long sessionId, Long taskId) {
}
