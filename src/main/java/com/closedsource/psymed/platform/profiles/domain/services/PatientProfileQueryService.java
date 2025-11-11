package com.closedsource.psymed.platform.profiles.domain.services;

import com.closedsource.psymed.platform.profiles.domain.model.aggregates.PatientProfile;
import com.closedsource.psymed.platform.profiles.domain.model.queries.*;


import java.util.List;
import java.util.Optional;

public interface PatientProfileQueryService {
    Optional<PatientProfile> handle(GetPatientProfileByIdQuery query);
    Optional<PatientProfile> handle(GetPatientProfileByAccountIdQuery query);
    List<PatientProfile> handle(GetAllPatientProfilesQuery query);
    Long handle(GetClinicalHistoryIdByPatientIdQuery query);
    List<PatientProfile> handle(GetPatientProfileByProfessionalIdQuery query);

}
