package com.closedsource.psymed.platform.medication.interfaces.rest.resources;

public record UpdatePillResource(
        String name,
        String description,
        String interval,
        String quantity
) {
    public UpdatePillResource {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name cannot be null or blank");
        if (description == null || description.isBlank())
            throw new IllegalArgumentException("Description cannot be null or blank");
    }
}

