package com.closedsource.psymed.platform.patientreport.domain.model.aggregates;

import com.closedsource.psymed.platform.patientreport.domain.model.commands.CreateBiologicalFunctionRecordCommand;
import com.closedsource.psymed.platform.patientreport.domain.model.valueobjects.BiologicalFunctionStatus;
import com.closedsource.psymed.platform.patientreport.domain.model.valueobjects.PatientId;
import com.closedsource.psymed.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.closedsource.psymed.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class BiologicalFunction extends AuditableAbstractAggregateRoot<BiologicalFunction> {

    @Getter
    @NotNull
    @Embedded
    BiologicalFunctionStatus status;

    @Getter
    @Embedded
    private PatientId patientId;


    public BiologicalFunction() {
        this.patientId = null;
        this.status = null;
    }

    public BiologicalFunction(Integer hunger, Integer hydration,
                              Integer sleep, Integer energy, Long patientId) {
        this.status = new BiologicalFunctionStatus(hunger, hydration, sleep, energy);
        this.patientId = new PatientId(patientId);
    }

    public BiologicalFunction(CreateBiologicalFunctionRecordCommand command) {
        this.status = new BiologicalFunctionStatus(command.hunger(), command.hydration(),
                command.sleep(), command.energy());
        this.patientId = new PatientId(command.patientId());
    }

    public void validateRecordAvailability(BiologicalFunction lastBiologicalFunction){
        Date actualDate = new Date();

        LocalDate currentDay = actualDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate lastBiologicalFunctionDay = lastBiologicalFunction.getCreatedAt().toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate();

        if(Objects.equals(currentDay, lastBiologicalFunctionDay))
            throw new IllegalStateException("You can't record a biological function twice in the same day");
    }

    public Long getLongPatientId() {
        return patientId.patientId();
    }

    public Integer getHunger() {
        return status.hunger();
    }

    public Integer getHydration() {
        return status.hydration();
    }

    public Integer getSleep() {
        return status.sleep();
    }

    public Integer getEnergy() {
        return status.energy();
    }
}
