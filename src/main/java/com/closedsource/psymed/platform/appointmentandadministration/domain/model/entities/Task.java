package com.closedsource.psymed.platform.appointmentandadministration.domain.model.entities;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.aggregates.Session;
import com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects.TaskStatus;
import com.closedsource.psymed.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Getter
public class Task extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Embedded
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "session_id")
    @NotNull
    private Session session;

    public Task(Session session, String title, String description) {
        this.session = session;
        this.title = title;
        this.description = description;
        this.status = new TaskStatus((short)0);
    }

    public void completeTask() {
        this.status = this.status.changeToCompleted();
    }




}
