package com.closedsource.psymed.platform.profiles.interfaces.rest.transform;

import com.closedsource.psymed.platform.profiles.domain.model.commands.AddClinicalHistoryToPatientCommand;
import com.closedsource.psymed.platform.profiles.interfaces.rest.resources.AddClinicalHistoryToPatientResource;

public class AddClinicalHistoryToPatientCommandFromResourceAssembler {
    public static AddClinicalHistoryToPatientCommand toCommandFromResource(Long patientId, AddClinicalHistoryToPatientResource resource) {
        return new AddClinicalHistoryToPatientCommand(patientId, resource.background(), resource.consultationReason(), resource.consultationDate());
    }
}
