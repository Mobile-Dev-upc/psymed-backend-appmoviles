package com.closedsource.psymed.platform.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Email(String email) {
    public Email {
        if(email == null || email.isBlank())
            throw new IllegalArgumentException("Email cannot be null or empty");
        if(!email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$"))
            throw new IllegalArgumentException("Email is not valid");
    }
}
