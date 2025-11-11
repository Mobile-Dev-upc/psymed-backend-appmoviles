package com.closedsource.psymed.platform.profiles.interfaces.rest.transform;

import com.closedsource.psymed.platform.profiles.domain.model.commands.UpdatePatientProfileCommand;
import com.closedsource.psymed.platform.profiles.interfaces.rest.resources.UpdatePatientProfileResource;

public class UpdatePatientProfileCommandFromResourceAssembler {
    public static UpdatePatientProfileCommand toCommandFromResource(Long id, UpdatePatientProfileResource resource) {
        return new UpdatePatientProfileCommand(
                id,
                resource.firstName(),
                resource.lastName(),
                resource.street(),
                resource.city(),
                resource.country(),
                resource.email()
        );
    }
}

