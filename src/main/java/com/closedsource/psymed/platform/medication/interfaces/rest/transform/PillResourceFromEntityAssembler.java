package com.closedsource.psymed.platform.medication.interfaces.rest.transform;

import com.closedsource.psymed.platform.medication.domain.model.aggregates.Pills;
import com.closedsource.psymed.platform.medication.interfaces.rest.resources.PillResource;

public class PillResourceFromEntityAssembler {
    public static PillResource toResourceFromEntity(Pills entity){
        return new PillResource(entity.getId(), entity.getName(), entity.getDescription(),entity.getPatientId(), entity.getInterval(), entity.getQuantity());
    }
}
