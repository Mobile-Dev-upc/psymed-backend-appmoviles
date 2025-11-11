package com.closedsource.psymed.platform.iam.interfaces.rest.transform;

import com.closedsource.psymed.platform.iam.domain.model.aggregate.Account;
import com.closedsource.psymed.platform.iam.interfaces.rest.resources.AccountResource;

public class AccountResourceFromEntityAssembler {
    public static AccountResource toResourceFromEntity(Account entity) {
        return new AccountResource(entity.getId(), entity.getUserName(), entity.getRoleInString());
    }
}
