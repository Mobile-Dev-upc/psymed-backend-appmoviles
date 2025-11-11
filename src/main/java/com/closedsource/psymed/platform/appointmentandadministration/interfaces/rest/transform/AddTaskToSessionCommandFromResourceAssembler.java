package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.AddTaskToSessionCommand;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.AddTaskToSessionResource;

public class AddTaskToSessionCommandFromResourceAssembler {
    public static AddTaskToSessionCommand toCommandFromResource(Long sessionId, AddTaskToSessionResource resource) {
        return new AddTaskToSessionCommand(sessionId, resource.title(), resource.description());
    }
}
