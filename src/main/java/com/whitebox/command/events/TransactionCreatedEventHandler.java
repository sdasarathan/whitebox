package com.whitebox.command.events;

import com.whitebox.command.entity.Account;
import com.whitebox.command.entity.Transaction;
import com.whitebox.command.repository.AccountRepository;
import com.whitebox.command.repository.TransactionRepository;
import com.whitebox.model.TransactionType;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@ProcessingGroup("transaction")
public class TransactionCreatedEventHandler {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

//    public TransactionCreatedEventHandler(AccountRepository accountRepository) {
//        this.transactionRepository = transactionRepository;
//    }

    @EventHandler
    public void on(TransactionCreatedEvent transactionCreatedEvent) throws Exception {
        System.out.println("AccountEventHandler:EventHandler");
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(transactionCreatedEvent, transaction);
        System.out.println("Id>"+transactionCreatedEvent.getId());
        System.out.println("Transaction Type>"+transactionCreatedEvent.getTransactionType());
        System.out.println("Amount >"+transactionCreatedEvent.getAmount());
        System.out.println("Account ID>"+transactionCreatedEvent.getAccountId());
        Account account = accountRepository.findById(transactionCreatedEvent.getAccountId()).get();
        BigDecimal currentBalance = account.getBalance();
        BigDecimal newBalance = null;
        if(transaction.getTransactionType()== TransactionType.CREDIT) {
           newBalance = currentBalance.add(transaction.getAmount());
        }else if(transaction.getTransactionType()==TransactionType.DEBIT){
           newBalance = currentBalance.subtract(transaction.getAmount());
        }
        account.setBalance(newBalance);
        accountRepository.save(account);
        transactionRepository.save(transaction);
    }

    @ExceptionHandler
    public void handle(Exception exception) throws Exception {
        throw exception;
    }
}
