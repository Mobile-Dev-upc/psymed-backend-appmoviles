package com.closedsource.psymed.platform.patientreport.application.queryservices;

import com.closedsource.psymed.platform.patientreport.domain.model.aggregates.MoodState;
import com.closedsource.psymed.platform.patientreport.domain.model.queries.GetAllMoodStatesByPatientIdQuery;
import com.closedsource.psymed.platform.patientreport.domain.services.MoodStateQueryService;
import com.closedsource.psymed.platform.patientreport.infrastructure.persistence.jpa.repositories.MoodStateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoodStateQueryServiceImpl implements MoodStateQueryService {
    private final MoodStateRepository moodStateRepository;

    public MoodStateQueryServiceImpl(MoodStateRepository moodStateRepository) {
        this.moodStateRepository = moodStateRepository;
    }
    @Override
    public List<MoodState> handle(GetAllMoodStatesByPatientIdQuery query) {
        return moodStateRepository.findAllByPatientId(query.patientId());
    }
}
