package com.closedsource.psymed.platform.profiles.domain.model.commands;

public record UpdatePatientProfileCommand(
        Long id,
        String firstName,
        String lastName,
        String street,
        String city,
        String country,
        String email
) {
}

