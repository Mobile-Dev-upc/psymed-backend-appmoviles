package com.closedsource.psymed.platform.medication.interfaces.rest.transform;

import com.closedsource.psymed.platform.medication.domain.model.commands.UpdatePillCommand;
import com.closedsource.psymed.platform.medication.interfaces.rest.resources.UpdatePillResource;

public class UpdatePillCommandFromResourceAssembler {
    public static UpdatePillCommand toCommandFromResource(Long pillId, UpdatePillResource resource) {
        return new UpdatePillCommand(
                pillId,
                resource.name(),
                resource.description(),
                resource.interval(),
                resource.quantity()
        );
    }
}

