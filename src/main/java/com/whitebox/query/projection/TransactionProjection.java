package com.whitebox.query.projection;

import com.whitebox.command.entity.Account;
import com.whitebox.command.entity.Transaction;
import com.whitebox.command.repository.AccountRepository;
import com.whitebox.command.repository.TransactionRepository;
import com.whitebox.model.AccountDto;
import com.whitebox.model.TransactionDto;
import com.whitebox.query.queries.GetAccountQuery;
import com.whitebox.query.queries.GetTransactionQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionProjection {

    private TransactionRepository transactionRepository;

    public TransactionProjection(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @QueryHandler
    public List<TransactionDto> gethandle(GetTransactionQuery getTransactionQuery){
        List<Transaction> accounts = transactionRepository.findAll();

        List<TransactionDto> transactionDtos = accounts.stream().map(transaction -> TransactionDto
                .builder()
                        .id(transaction.getId())
                        .accountId(transaction.getAccountId())
                        .amount(transaction.getAmount())
                        .transactionDate(transaction.getTransactionDate())
                        .build())
                        .collect(Collectors.toList());
        return transactionDtos;
    }
}
