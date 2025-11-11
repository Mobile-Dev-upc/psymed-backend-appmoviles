package com.closedsource.psymed.platform.clinicalhistory.domain.service;

import com.closedsource.psymed.platform.clinicalhistory.domain.model.aggregates.ClinicalHistory;
import com.closedsource.psymed.platform.clinicalhistory.domain.model.queries.GetClinicalHistoryByIdQuery;
import com.closedsource.psymed.platform.clinicalhistory.domain.model.queries.GetClinicalHistoryByPatientIdQuery;

import java.util.Optional;

public interface ClinicalHistoryQueryService {
    Optional<ClinicalHistory> handle(GetClinicalHistoryByIdQuery query);
    Optional<ClinicalHistory> handle(GetClinicalHistoryByPatientIdQuery query);
}
