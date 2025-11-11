package com.closedsource.psymed.platform.profiles.domain.model.commands;

public record CreateProfessionalProfileCommand(String firstName, String lastName, String street, String city, String country, String email, String username, String password) {

}
