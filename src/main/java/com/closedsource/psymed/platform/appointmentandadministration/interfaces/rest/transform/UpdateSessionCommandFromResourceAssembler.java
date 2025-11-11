package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.commands.UpdateSessionCommand;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.UpdateSessionResource;

public class UpdateSessionCommandFromResourceAssembler {

    public static UpdateSessionCommand toCommandFromResource(
            Long professionalId,
            Long patientId,
            Long sessionId,
            UpdateSessionResource resource
    ) {
        return new UpdateSessionCommand(
                sessionId,
                patientId,
                professionalId,
                resource.appointmentDate(),
                resource.sessionTime()
        );
    }
}

