package com.closedsource.psymed.platform.iam.application.internal.queryservices;

import com.closedsource.psymed.platform.iam.domain.model.aggregate.Account;
import com.closedsource.psymed.platform.iam.domain.model.queries.GetAccountByIdQuery;
import com.closedsource.psymed.platform.iam.domain.service.AccountQueryService;
import com.closedsource.psymed.platform.iam.infrastructure.persistence.jpa.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountQueryServiceImpl implements AccountQueryService {
    private final AccountRepository accountRepository;

    public AccountQueryServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> handle(GetAccountByIdQuery query) {
//        return accountRepository.findById(query.id());
        var accountFound = accountRepository.findById(query.id());
        if(accountFound.isEmpty()) throw new IllegalArgumentException("Account not found");
        return accountFound;
    }
}
