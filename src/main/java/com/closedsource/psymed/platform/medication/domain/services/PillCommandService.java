package com.closedsource.psymed.platform.medication.domain.services;

import com.closedsource.psymed.platform.medication.domain.model.commands.CreatePillsCommand;
import com.closedsource.psymed.platform.medication.domain.model.commands.DeletePillsCommand;

public interface PillCommandService {
    Long handle (CreatePillsCommand command);

    void handle (DeletePillsCommand command);


}
