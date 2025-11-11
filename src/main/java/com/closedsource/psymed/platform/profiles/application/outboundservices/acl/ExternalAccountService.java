package com.closedsource.psymed.platform.profiles.application.outboundservices.acl;

import com.closedsource.psymed.platform.iam.interfaces.acl.IamContextFacade;
import com.closedsource.psymed.platform.profiles.domain.model.valueobjects.AccountId;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ExternalAccountService {

    private final IamContextFacade iamContextFacade;

    public ExternalAccountService(IamContextFacade iamContextFacade) {
        this.iamContextFacade = iamContextFacade;
    }

    public Optional<AccountId> createAccount(String username, String password, String role) {
        var accountId = iamContextFacade.createAccount(username, password, role);
        return Optional.of(new AccountId(accountId));
    }

}
