package com.closedsource.psymed.platform.patientreport.interfaces.rest.transform;

import com.closedsource.psymed.platform.patientreport.domain.model.commands.CreateMoodStateRecordCommand;
import com.closedsource.psymed.platform.patientreport.interfaces.rest.resources.CreateMoodStateRecordResource;
import com.closedsource.psymed.platform.patientreport.interfaces.rest.resources.MoodStateResource;

public class CreateMoodStateRecordCommandFromResourceAssembler {
    public static CreateMoodStateRecordCommand toCommandFromResource(CreateMoodStateRecordResource resource, Long patientId) {
        return new CreateMoodStateRecordCommand(resource.status(), patientId);
    }
}
