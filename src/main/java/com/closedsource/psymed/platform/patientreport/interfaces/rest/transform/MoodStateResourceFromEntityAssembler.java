package com.closedsource.psymed.platform.patientreport.interfaces.rest.transform;

import com.closedsource.psymed.platform.patientreport.domain.model.aggregates.MoodState;
import com.closedsource.psymed.platform.patientreport.interfaces.rest.resources.MoodStateResource;

public class MoodStateResourceFromEntityAssembler {
    public static MoodStateResource toResourceFromEntity (MoodState entity) {
        return new MoodStateResource(entity.getId(), entity.getStatus());
    }
}
