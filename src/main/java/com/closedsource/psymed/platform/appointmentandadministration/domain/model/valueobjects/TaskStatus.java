package com.closedsource.psymed.platform.appointmentandadministration.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record TaskStatus(Short status) {
    public TaskStatus {
        if (status < 0 || status > 1) throw new IllegalArgumentException("Invalid status");
    }

    public TaskStatus changeToCompleted() {
        return new TaskStatus((short) 1);
    }
}
