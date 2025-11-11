package com.closedsource.psymed.platform.patientreport.domain.services;

import com.closedsource.psymed.platform.patientreport.domain.model.aggregates.MoodState;
import com.closedsource.psymed.platform.patientreport.domain.model.queries.GetAllMoodStatesByPatientIdQuery;

import java.util.List;

public interface MoodStateQueryService {
    List<MoodState> handle(GetAllMoodStatesByPatientIdQuery query);
}
