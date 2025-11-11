package com.closedsource.psymed.platform.clinicalhistory.domain.service;

import com.closedsource.psymed.platform.clinicalhistory.domain.model.aggregates.ClinicalHistory;
import com.closedsource.psymed.platform.clinicalhistory.domain.model.commands.CreateClinicalHistoryCommand;

import java.util.Optional;

public interface ClinicalHistoryCommandService {
    Optional<ClinicalHistory> handle(CreateClinicalHistoryCommand command);
}
