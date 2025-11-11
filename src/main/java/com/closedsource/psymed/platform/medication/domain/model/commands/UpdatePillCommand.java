package com.closedsource.psymed.platform.medication.domain.model.commands;

public record UpdatePillCommand(
        Long pillId,
        String name,
        String description,
        String interval,
        String quantity
) {
}

