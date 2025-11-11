package com.closedsource.psymed.platform.clinicalhistory.domain.model.commands;

import java.time.LocalDate;

public record CreateClinicalHistoryCommand(
        String background,
        String consultationReason,
        LocalDate consultationDate
) { }
