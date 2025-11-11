package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.UpdateNoteCommand;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.UpdateNoteResource;

public class UpdateNoteCommandFromResourceAssembler {
    public static UpdateNoteCommand toCommandFromResource(Long sessionId, UpdateNoteResource resource) {
        return new UpdateNoteCommand(sessionId, resource.title(), resource.description());
    }
}

