package com.closedsource.psymed.platform.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record AccountId(Long accountId) {
    public AccountId {
        if (accountId == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
    }
}
