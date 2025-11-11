package com.closedsource.psymed.platform.profiles.interfaces.rest.transform;

import com.closedsource.psymed.platform.profiles.domain.model.commands.CreatePatientProfileCommand;
import com.closedsource.psymed.platform.profiles.interfaces.rest.resources.CreatePatientProfileResource;

public class CreatePatientProfileCommandFromResourceAssembler {
    public static CreatePatientProfileCommand toCommandFromResource(CreatePatientProfileResource resource) {
        return new CreatePatientProfileCommand(resource.firstName(), resource.lastName(), resource.street(),
                resource.city(), resource.country(), resource.email(), resource.username(), resource.password(), resource.professionalId());
    }
}
