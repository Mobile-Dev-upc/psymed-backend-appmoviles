package com.closedsource.psymed.platform.profiles.domain.services;

import com.closedsource.psymed.platform.profiles.domain.model.aggregates.ProfessionalProfile;
import com.closedsource.psymed.platform.profiles.domain.model.commands.CheckProfessionalProfileByIdCommand;
import com.closedsource.psymed.platform.profiles.domain.model.commands.CreateProfessionalProfileCommand;

import java.util.Optional;

public interface ProfessionalProfileCommandService {
    Optional<ProfessionalProfile> handle(CreateProfessionalProfileCommand command);
    boolean handle(CheckProfessionalProfileByIdCommand command);
}
