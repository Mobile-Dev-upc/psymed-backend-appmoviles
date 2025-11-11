package com.closedsource.psymed.platform.clinicalhistory.domain.model.aggregates;


import com.closedsource.psymed.platform.clinicalhistory.domain.model.commands.CreateClinicalHistoryCommand;
import com.closedsource.psymed.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
public class ClinicalHistory extends AuditableAbstractAggregateRoot<ClinicalHistory>{

    @NotBlank
    @Column(nullable = false)
    String background;

    @NotBlank
    @Column(nullable = false)
    String consultationReason;

    @Column(nullable = false)
    LocalDate consultationDate;

    public ClinicalHistory() {
    }


    public ClinicalHistory(CreateClinicalHistoryCommand command) {
        this.background = command.background();
        this.consultationReason = command.consultationReason();
        this.consultationDate = command.consultationDate();
    }

}
