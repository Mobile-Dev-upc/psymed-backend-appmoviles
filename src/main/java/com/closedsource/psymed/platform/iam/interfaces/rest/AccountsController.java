package com.closedsource.psymed.platform.iam.interfaces.rest;

import com.closedsource.psymed.platform.iam.domain.model.queries.GetAccountByIdQuery;
import com.closedsource.psymed.platform.iam.domain.service.AccountQueryService;
import com.closedsource.psymed.platform.iam.interfaces.rest.resources.AccountResource;
import com.closedsource.psymed.platform.iam.interfaces.rest.transform.AccountResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/accounts")
@Tag(name = "Accounts", description = "Accounts Endpoints")
public class AccountsController {
    private final AccountQueryService accountQueryService;
    public AccountsController(AccountQueryService accountQueryService) {
        this.accountQueryService = accountQueryService;
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResource> getAccountById(@PathVariable Long accountId) {
        var getAccountByIdQuery = new GetAccountByIdQuery(accountId);
        var account = accountQueryService.handle(getAccountByIdQuery);
        if(account.isEmpty()) return ResponseEntity.notFound().build();

        var userResource = AccountResourceFromEntityAssembler.toResourceFromEntity(account.get());
        return ResponseEntity.ok(userResource);
    }
}
