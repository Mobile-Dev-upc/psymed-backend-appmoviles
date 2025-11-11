package com.closedsource.psymed.platform.medication.interfaces.rest.transform;

import com.closedsource.psymed.platform.medication.domain.model.commands.CreatePillsCommand;
import com.closedsource.psymed.platform.medication.interfaces.rest.resources.CreatePillResource;

public class CreatePillCommandFromResourceAssembler {
    public static CreatePillsCommand toCommandFromResource (CreatePillResource resource)
    {
        return new CreatePillsCommand(resource.name(), resource.description(), resource.patientId(), resource.interval(), resource.quantity());
    }
}
