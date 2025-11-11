package com.closedsource.psymed.platform.patientreport.interfaces.rest.transform;

import com.closedsource.psymed.platform.patientreport.domain.model.aggregates.BiologicalFunction;
import com.closedsource.psymed.platform.patientreport.domain.model.commands.CreateBiologicalFunctionRecordCommand;
import com.closedsource.psymed.platform.patientreport.interfaces.rest.resources.BiologicalFunctionResource;
import com.closedsource.psymed.platform.patientreport.interfaces.rest.resources.CreateBiologicalFunctionRecordResource;

public class CreateBiologicalFunctionRecordCommandFromResourceAssembler {
    public static CreateBiologicalFunctionRecordCommand toCommandFromResource(CreateBiologicalFunctionRecordResource resource, Long patientId) {
        return new CreateBiologicalFunctionRecordCommand(resource.hunger(), resource.hydration(), resource.sleep(),resource.energy(), patientId);
    }
}
