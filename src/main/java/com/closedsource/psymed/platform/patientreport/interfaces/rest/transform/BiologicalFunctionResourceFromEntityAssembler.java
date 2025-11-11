package com.closedsource.psymed.platform.patientreport.interfaces.rest.transform;

import com.closedsource.psymed.platform.patientreport.domain.model.aggregates.BiologicalFunction;
import com.closedsource.psymed.platform.patientreport.interfaces.rest.resources.BiologicalFunctionResource;

public class BiologicalFunctionResourceFromEntityAssembler {
    public static BiologicalFunctionResource toResourceFromEntity(BiologicalFunction entity) {
        return new BiologicalFunctionResource(entity.getId(), entity.getHunger()
                , entity.getHydration(), entity.getSleep(), entity.getEnergy());
    }
}
