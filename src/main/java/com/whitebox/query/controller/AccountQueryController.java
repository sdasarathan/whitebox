package com.whitebox.query.controller;


import com.whitebox.model.AccountDto;
import com.whitebox.query.queries.GetAccountByIdQuery;
import com.whitebox.query.queries.GetAccountQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
class AccountQueryController {

    private QueryGateway queryGateway;

    public AccountQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/accounts")
    public List<AccountDto> listAllAccounts() {
        GetAccountQuery getAccountQuery = new GetAccountQuery();
        List<AccountDto> accountDtos = queryGateway.query(getAccountQuery, ResponseTypes.multipleInstancesOf(AccountDto.class)).join();
        return accountDtos;
    }

    @GetMapping("/accounts/{id}")
    public BigDecimal getBalanceForAccount(@PathVariable String id) {
        GetAccountByIdQuery getAccountByIdQuery = new GetAccountByIdQuery();
        getAccountByIdQuery.setId(id);
        AccountDto accountDto = queryGateway.query(getAccountByIdQuery, ResponseTypes.instanceOf(AccountDto.class)).join();
        return accountDto.getBalance();
    }
}
