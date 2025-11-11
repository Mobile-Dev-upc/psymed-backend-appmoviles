package com.closedsource.psymed.platform.patientreport.domain.services;

import com.closedsource.psymed.platform.patientreport.domain.model.aggregates.BiologicalFunction;
import com.closedsource.psymed.platform.patientreport.domain.model.commands.CreateBiologicalFunctionRecordCommand;

import java.util.Optional;

public interface BiologicalFunctionCommandService {
    Optional<BiologicalFunction> handle(CreateBiologicalFunctionRecordCommand command);
}
