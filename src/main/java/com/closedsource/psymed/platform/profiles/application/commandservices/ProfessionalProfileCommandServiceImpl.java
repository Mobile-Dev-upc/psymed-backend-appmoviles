package com.closedsource.psymed.platform.profiles.application.commandservices;

import com.closedsource.psymed.platform.profiles.application.outboundservices.acl.ExternalAccountService;
import com.closedsource.psymed.platform.profiles.domain.model.aggregates.ProfessionalProfile;
import com.closedsource.psymed.platform.profiles.domain.model.commands.CheckProfessionalProfileByIdCommand;
import com.closedsource.psymed.platform.profiles.domain.model.commands.CreateProfessionalProfileCommand;
import com.closedsource.psymed.platform.profiles.domain.model.valueobjects.Email;
import com.closedsource.psymed.platform.profiles.domain.services.ProfessionalProfileCommandService;
import com.closedsource.psymed.platform.profiles.infrastructure.persistence.jpa.repositories.PatientProfileRepository;
import com.closedsource.psymed.platform.profiles.infrastructure.persistence.jpa.repositories.ProfessionalProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class ProfessionalProfileCommandServiceImpl implements ProfessionalProfileCommandService {

    private final ProfessionalProfileRepository professionalProfileRepository;
    private final PatientProfileRepository patientProfileRepository;
    private final ExternalAccountService externalAccountService;

    public ProfessionalProfileCommandServiceImpl(ProfessionalProfileRepository professionalProfileRepository,
                                                 PatientProfileRepository patientProfileRepository,
                                                 ExternalAccountService externalAccountService) {
        this.professionalProfileRepository = professionalProfileRepository;
        this.patientProfileRepository = patientProfileRepository;
        this.externalAccountService = externalAccountService;
    }

    @Override
    @Transactional
    public Optional<ProfessionalProfile> handle(CreateProfessionalProfileCommand command) {
        var emailAddress = new Email(command.email());
        if(professionalProfileRepository.existsByEmail(emailAddress) || patientProfileRepository.existsByEmail(emailAddress))
            throw new IllegalArgumentException("Email already exists");

        var accountId = externalAccountService.createAccount(command.username(), command.password(), "ROLE_PROFESSIONAL");

        var profile = new ProfessionalProfile(command, accountId.get());

        professionalProfileRepository.save(profile);

        return Optional.of(profile);
    }

    @Override
    public boolean handle(CheckProfessionalProfileByIdCommand command) {
        return this.professionalProfileRepository.existsById(command.id());
    }
}
