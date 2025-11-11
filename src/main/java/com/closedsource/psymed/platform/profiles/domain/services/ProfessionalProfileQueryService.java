package com.closedsource.psymed.platform.profiles.domain.services;

import com.closedsource.psymed.platform.profiles.domain.model.aggregates.ProfessionalProfile;
import com.closedsource.psymed.platform.profiles.domain.model.queries.GetProfessionalProfileByAccountIdQuery;
import com.closedsource.psymed.platform.profiles.domain.model.queries.GetProfessionalProfileByIdQuery;

import java.util.Optional;

public interface ProfessionalProfileQueryService {
    Optional<ProfessionalProfile> handle(GetProfessionalProfileByIdQuery query);

    Optional<ProfessionalProfile> handle(GetProfessionalProfileByAccountIdQuery query);

}
