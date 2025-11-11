package com.closedsource.psymed.platform.medication.application.internal.commandservices;

import com.closedsource.psymed.platform.medication.domain.model.aggregates.Pills;
import com.closedsource.psymed.platform.medication.domain.model.commands.CreatePillsCommand;
import com.closedsource.psymed.platform.medication.domain.model.commands.DeletePillsCommand;
import com.closedsource.psymed.platform.medication.domain.model.commands.UpdatePillCommand;
import com.closedsource.psymed.platform.medication.domain.services.PillCommandService;
import com.closedsource.psymed.platform.medication.infrastructure.persistence.jpa.repositories.PillRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public Optional<Pills> handle(UpdatePillCommand command) {
        if (!pillRepository.existsById(command.pillId()))
            throw new IllegalStateException("The medication doesn't exist");
        
        var pillToUpdate = pillRepository.findById(command.pillId());
        
        if (pillToUpdate.isEmpty())
            throw new IllegalStateException("The medication doesn't exist");
        
        var updatedPill = pillToUpdate.get();
        
        try {
            updatedPill.updateInformation(
                command.name(),
                command.description(),
                command.interval(),
                command.quantity()
            );
            pillRepository.save(updatedPill);
            return Optional.of(updatedPill);
        } catch (Exception e) {
            throw new IllegalArgumentException("An error occurred during update: %s".formatted(e.getMessage()));
        }
    }
}
