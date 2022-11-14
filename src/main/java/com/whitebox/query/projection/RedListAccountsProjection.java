package com.whitebox.query.projection;

import com.whitebox.command.entity.Account;
import com.whitebox.command.repository.AccountRepository;
import com.whitebox.model.AccountDto;
import com.whitebox.query.queries.GetRedListAccountsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RedListAccountsProjection {

    private AccountRepository accountRepository;

    public RedListAccountsProjection(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @QueryHandler
    public List<AccountDto> handle(GetRedListAccountsQuery getRedListAccountsQuery) {
        List<Account> accounts = accountRepository.findAll();

        List<AccountDto> accountDtos = accounts.stream().map(account -> AccountDto
                        .builder()
                        .id(account.getId())
                        .balance(account.getBalance())
                        .holderName(account.getHolderName())
                        .build())
                        .filter(a -> {
                            return BigDecimal.ZERO.compareTo(a.getBalance()) > 0;
                        })
                .collect(Collectors.toList());
        return accountDtos;
    }
}
