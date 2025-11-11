package com.closedsource.psymed.platform.patientreport.domain.services;

import com.closedsource.psymed.platform.patientreport.domain.model.aggregates.MoodState;
import com.closedsource.psymed.platform.patientreport.domain.model.commands.CreateMoodStateRecordCommand;

import java.util.Optional;

public interface MoodStateCommandService {
    Optional<MoodState> handle(CreateMoodStateRecordCommand command);

}
