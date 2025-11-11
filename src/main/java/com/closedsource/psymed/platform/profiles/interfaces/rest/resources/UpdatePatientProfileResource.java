package com.closedsource.psymed.platform.profiles.interfaces.rest.resources;

public record UpdatePatientProfileResource(
        String firstName,
        String lastName,
        String street,
        String city,
        String country,
        String email
) {
}

