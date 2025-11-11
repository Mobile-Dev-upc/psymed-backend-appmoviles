package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.UpdateTaskStatusToIncompleteCommand;

public class UpdateTaskStatusToIncompleteCommandFromResourceAssembler {
    public static UpdateTaskStatusToIncompleteCommand toCommandFromResource(Long sessionId, Long taskId) {
        return new UpdateTaskStatusToIncompleteCommand(sessionId, taskId);
    }
}

