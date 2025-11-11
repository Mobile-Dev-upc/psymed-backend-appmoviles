package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.UpdateTaskCommand;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.UpdateTaskResource;

public class UpdateTaskCommandFromResourceAssembler {
    public static UpdateTaskCommand toCommandFromResource(Long sessionId, Long taskId, UpdateTaskResource resource) {
        return new UpdateTaskCommand(
                sessionId,
                taskId,
                resource.title(),
                resource.description()
        );
    }
}

