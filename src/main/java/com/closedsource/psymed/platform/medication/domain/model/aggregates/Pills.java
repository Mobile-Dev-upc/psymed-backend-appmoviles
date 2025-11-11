package com.closedsource.psymed.platform.medication.domain.model.aggregates;

import com.closedsource.psymed.platform.medication.domain.model.commands.CreatePillsCommand;
import com.closedsource.psymed.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;


@Getter
@Entity
//@NoArgsConstructor
public class Pills extends AuditableAbstractAggregateRoot<Pills> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Long patientId;

    @Column(name = "`dosage`")
    private String interval;

    private String quantity;

    public Pills(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Pills(CreatePillsCommand command) {
        this.name = command.name();
        this.description = command.description();
        this.patientId = command.patientId();
        this.interval = command.interval();
        this.quantity = command.quantity();
    }

    public Pills() {

    }
}
