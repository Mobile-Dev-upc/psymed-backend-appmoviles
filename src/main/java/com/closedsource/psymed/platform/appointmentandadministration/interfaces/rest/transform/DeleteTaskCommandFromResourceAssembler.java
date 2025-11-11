package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.DeleteTaskCommand;

public class DeleteTaskCommandFromResourceAssembler {
    public static DeleteTaskCommand toCommandFromResource(Long sessionId, Long taskId) {
        return new DeleteTaskCommand(sessionId, taskId);
    }
}

