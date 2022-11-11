package com.whitebox.query.projection;

import com.whitebox.command.entity.Account;
import com.whitebox.command.repository.AccountRepository;
import com.whitebox.model.AccountDto;
import com.whitebox.query.queries.GetAccountByIdQuery;
import com.whitebox.query.queries.GetAccountQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class AccountByIdProjection {

    private AccountRepository accountRepository;

    public AccountByIdProjection(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @QueryHandler
    public AccountDto handle(GetAccountByIdQuery getAccountByIdQuery){
        Account account = accountRepository.findById(getAccountByIdQuery.getId()).get();

        AccountDto accountDto = AccountDto
                        .builder()
                        .id(account.getId())
                        .balance(account.getBalance())
                        .holderName(account.getHolderName())
                        .build();
        return accountDto;
    }
}
