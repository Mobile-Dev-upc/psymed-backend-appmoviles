package com.closedsource.psymed.platform.profiles.application.queryservices;

import com.closedsource.psymed.platform.profiles.domain.model.aggregates.PatientProfile;
import com.closedsource.psymed.platform.profiles.domain.model.queries.*;
import com.closedsource.psymed.platform.profiles.domain.model.queries.GetClinicalHistoryIdByPatientIdQuery;
import com.closedsource.psymed.platform.profiles.domain.services.PatientProfileQueryService;
import com.closedsource.psymed.platform.profiles.infrastructure.persistence.jpa.repositories.PatientProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientProfileQueryServiceImpl implements PatientProfileQueryService {

    private final PatientProfileRepository patientProfileRepository;

    public PatientProfileQueryServiceImpl (PatientProfileRepository patientProfileRepository) {
        this.patientProfileRepository = patientProfileRepository;
    }

    @Override
    public Optional<PatientProfile> handle(GetPatientProfileByIdQuery query) {
        return patientProfileRepository.findById(query.id());
    }

    @Override
    public List<PatientProfile> handle(GetAllPatientProfilesQuery query) {
        return patientProfileRepository.findAll();
    }

    @Override
    public Long handle(GetClinicalHistoryIdByPatientIdQuery query) {
        return patientProfileRepository.findClinicalHistoryIdById(query.patientId());
    }

    public Optional<PatientProfile> handle(GetPatientProfileByAccountIdQuery query) {
        return patientProfileRepository.findByAccountId(query.accountId());
    }

    public List<PatientProfile> handle(GetPatientProfileByProfessionalIdQuery query) {
        return patientProfileRepository.findByProfessionalId(query.professionalId());
    }




}
