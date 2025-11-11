package com.closedsource.psymed.platform.profiles.domain.model.commands;

public record CreatePatientProfileCommand(String firstName, String lastName, String street, String city, String country, String email, String username, String password, Long professionalId) {
}
