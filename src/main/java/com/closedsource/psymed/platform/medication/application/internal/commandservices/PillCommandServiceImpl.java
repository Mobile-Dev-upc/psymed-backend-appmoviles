package com.closedsource.psymed.platform.medication.application.internal.commandservices;

import com.closedsource.psymed.platform.medication.domain.model.aggregates.Pills;
import com.closedsource.psymed.platform.medication.domain.model.commands.CreatePillsCommand;
import com.closedsource.psymed.platform.medication.domain.model.commands.DeletePillsCommand;
import com.closedsource.psymed.platform.medication.domain.services.PillCommandService;
import com.closedsource.psymed.platform.medication.infrastructure.persistence.jpa.repositories.PillRepository;
import org.springframework.stereotype.Service;

@Service
public class PillCommandServiceImpl implements PillCommandService {

    private final PillRepository pillRepository;

    public PillCommandServiceImpl(PillRepository pillRepository){
        this.pillRepository = pillRepository;
    }
    @Override
    public Long handle(CreatePillsCommand command) {
        if (pillRepository.existsByName(command.name()))
            throw new IllegalArgumentException("There is a medication with the same name");
        var medication = new Pills(command);
        try {
            pillRepository.save(medication);
            return medication.getId();
        }catch(Exception e){
            throw new IllegalArgumentException(String.format("Error creating the medication %s", e.getMessage()));
        }
    }

    @Override
    public void handle(DeletePillsCommand command) {
        if(!pillRepository.existsById(command.medicationId()))
            throw new IllegalStateException("The medication doesn't exist");
        try {
            pillRepository.deleteById(command.medicationId());
        } catch(Exception e)
        {
            throw new IllegalArgumentException("An error occurred during delete: %s".formatted(e.getMessage()));
        }
    }
}
