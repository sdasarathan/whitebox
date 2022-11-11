package com.whitebox.query.projection;

import com.whitebox.command.entity.Account;
import com.whitebox.command.repository.AccountRepository;
import com.whitebox.model.AccountDto;
import com.whitebox.query.queries.GetAccountQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountProjection {

    private AccountRepository accountRepository;

    public AccountProjection(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @QueryHandler
    public List<AccountDto> handle(GetAccountQuery getAccountQuery){
        List<Account> accounts = accountRepository.findAll();

        List<AccountDto> accountDtos = accounts.stream().map(account -> AccountDto
                .builder()
                        .id(account.getId())
                        .balance(account.getBalance())
                        .holderName(account.getHolderName())
                            .build())
                            .collect(Collectors.toList());
        return accountDtos;
    }
}
