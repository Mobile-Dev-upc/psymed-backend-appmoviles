package com.closedsource.psymed.platform.medication.domain.services;

import com.closedsource.psymed.platform.medication.domain.model.aggregates.Pills;
import com.closedsource.psymed.platform.medication.domain.model.queries.GetAllPillsQuery;
import com.closedsource.psymed.platform.medication.domain.model.queries.GetPillsByIdQuery;
import com.closedsource.psymed.platform.medication.domain.model.queries.GetPillByNameQuery;
import com.closedsource.psymed.platform.medication.domain.model.queries.GetPillsByPatientId;

import java.util.List;
import java.util.Optional;

public interface PillQueryService {

    Optional<Pills> handle(GetPillByNameQuery query);

    List<Pills> handle(GetAllPillsQuery query);

    Optional<Pills> handle(GetPillsByIdQuery getPillsByIdQuery);

    List<Pills> handle(GetPillsByPatientId getPillsByPatientId);
}
