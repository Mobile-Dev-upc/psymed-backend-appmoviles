package com.closedsource.psymed.platform.profiles.interfaces.rest.resources;

import com.closedsource.psymed.platform.profiles.domain.model.valueobjects.AccountId;

public record ProfileResource(
        Long id,
        String fullName,
        String email,
        String streetAddress,
        AccountId accountId,
        Long professionalId
) { }
