package com.closedsource.psymed.platform.profiles.domain.services;

import com.closedsource.psymed.platform.profiles.domain.model.aggregates.PatientProfile;
import com.closedsource.psymed.platform.profiles.domain.model.commands.AddClinicalHistoryToPatientCommand;
import com.closedsource.psymed.platform.profiles.domain.model.commands.CheckPatientProfileByIdCommand;
import com.closedsource.psymed.platform.profiles.domain.model.commands.CreatePatientProfileCommand;
import com.closedsource.psymed.platform.profiles.domain.model.commands.UpdatePatientProfileCommand;
import com.closedsource.psymed.platform.profiles.domain.model.commands.DeletePatientProfileCommand;

import java.util.Optional;

public interface PatientProfileCommandService {
    Optional<PatientProfile> handle(CreatePatientProfileCommand command);
    boolean handle(CheckPatientProfileByIdCommand command);
    void handle(AddClinicalHistoryToPatientCommand command);
    Optional<PatientProfile> handle(UpdatePatientProfileCommand command);
    void handle(DeletePatientProfileCommand command);
}
