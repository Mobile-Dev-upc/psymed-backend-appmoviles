package com.closedsource.psymed.platform.profiles.domain.model.queries;

import com.closedsource.psymed.platform.profiles.domain.model.valueobjects.AccountId;

public record GetPatientProfileByAccountIdQuery(AccountId accountId) {
}
