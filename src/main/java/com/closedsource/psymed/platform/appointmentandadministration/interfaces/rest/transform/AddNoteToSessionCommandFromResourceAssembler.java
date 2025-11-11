package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.AddNoteToSessionCommand;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.AddNoteToSessionResource;

public class AddNoteToSessionCommandFromResourceAssembler {
    public static AddNoteToSessionCommand toCommandFromResource(Long sessionId, AddNoteToSessionResource resource) {
        return new AddNoteToSessionCommand(sessionId,  resource.title(), resource.description());
    }
}
