package com.whitebox.query.controller;


import com.whitebox.model.AccountDto;
import com.whitebox.model.TransactionDto;
import com.whitebox.model.TransactionFilterRequest;
import com.whitebox.query.queries.*;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/")
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

    @GetMapping("/transactions")
    public List<TransactionDto> listAllTransaction() {
        GetTransactionQuery getTransactionQuery = new GetTransactionQuery();
        List<TransactionDto> transactionDtos = queryGateway.query(getTransactionQuery, ResponseTypes.multipleInstancesOf(TransactionDto.class)).join();
        return transactionDtos;
    }

    @GetMapping("/transactionsbydate")
    public List<TransactionDto> getTransactionByAccountAndDate(@RequestBody TransactionFilterRequest transactionFilterRequest) {
        GetTransactionFilterQuery getTransactionFilterQuery = new GetTransactionFilterQuery();
        getTransactionFilterQuery.setAccountId(transactionFilterRequest.getAccountId());
        getTransactionFilterQuery.setDateFrom(transactionFilterRequest.getDateFrom());
        List<TransactionDto> transactionDtos = queryGateway.query(getTransactionFilterQuery, ResponseTypes.multipleInstancesOf(TransactionDto.class)).join();
        return transactionDtos;
    }

    @GetMapping("/redlistaccounts")
    public List<AccountDto> getRedListAccounts() {
        GetRedListAccountsQuery getRedListAccountQuery = new GetRedListAccountsQuery();
        List<AccountDto> accountDtos = queryGateway.query(getRedListAccountQuery, ResponseTypes.multipleInstancesOf(AccountDto.class)).join();
        return accountDtos;
    }
}
