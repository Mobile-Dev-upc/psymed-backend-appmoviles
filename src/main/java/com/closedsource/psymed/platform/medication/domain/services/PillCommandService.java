package com.closedsource.psymed.platform.medication.domain.services;

import com.closedsource.psymed.platform.medication.domain.model.aggregates.Pills;
import com.closedsource.psymed.platform.medication.domain.model.commands.CreatePillsCommand;
import com.closedsource.psymed.platform.medication.domain.model.commands.DeletePillsCommand;
import com.closedsource.psymed.platform.medication.domain.model.commands.UpdatePillCommand;

import java.util.Optional;

public interface PillCommandService {
    Long handle (CreatePillsCommand command);

    void handle (DeletePillsCommand command);

    Optional<Pills> handle (UpdatePillCommand command);
}
