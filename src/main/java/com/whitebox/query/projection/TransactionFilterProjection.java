package com.whitebox.query.projection;

import com.whitebox.command.entity.Transaction;
import com.whitebox.command.repository.TransactionRepository;
import com.whitebox.model.TransactionDto;
import com.whitebox.query.queries.GetTransactionFilterQuery;
import com.whitebox.query.queries.GetTransactionQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionFilterProjection {

    private TransactionRepository transactionRepository;

    public TransactionFilterProjection(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @QueryHandler
    public List<TransactionDto> getHandler(GetTransactionFilterQuery getTransactionFilterQuery){
        List<Transaction> transactions = transactionRepository.findAllByAccountId(getTransactionFilterQuery.getAccountId());
        List<TransactionDto> transactionDtos = transactions.stream().map(transaction -> TransactionDto
                .builder()
                        .id(transaction.getId())
                        .accountId(transaction.getAccountId())
                        .amount(transaction.getAmount())
                        .transactionDate(transaction.getTransactionDate())
                        .transactionType(transaction.getTransactionType())
                        .build())
                        .filter(t -> {
                            if(t.getTransactionDate()!=null) {
                                return t.getTransactionDate().after(getTransactionFilterQuery.getDateFrom());
                            }else{
                                return false;
                            }
                        })
                        .collect(Collectors.toList());

        return transactionDtos;
    }
}
