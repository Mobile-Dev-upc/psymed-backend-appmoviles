package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.UpdateTaskStatusToCompleteCommand;

public class UpdateTaskStatusToCompleteCommandFromResourceAssembler {
    public static UpdateTaskStatusToCompleteCommand toCommandFromResource(Long sessionId, Long taskId) {
        return new UpdateTaskStatusToCompleteCommand(sessionId, taskId);
    }
}
