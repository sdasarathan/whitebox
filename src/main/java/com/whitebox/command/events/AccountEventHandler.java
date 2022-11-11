package com.whitebox.command.events;

import com.whitebox.command.entity.Account;
import com.whitebox.command.repository.AccountRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("account")
public class AccountEventHandler {

    private AccountRepository accountRepository;

    public AccountEventHandler(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @EventHandler
    public void on(AccountCreatedEvent accountCreatedEvent) throws Exception {
        System.out.println("AccountEventHandler:EventHandler");
        Account account = new Account();
        BeanUtils.copyProperties(accountCreatedEvent, account);
        System.out.println("Id>"+accountCreatedEvent.getId());
        System.out.println("HolderName>"+accountCreatedEvent.getHolderName());
        System.out.println("Balance>"+accountCreatedEvent.getBalance());
        accountRepository.save(account);
        throw new Exception("Testing exception");
    }

    @ExceptionHandler
    public void handle(Exception exception) throws Exception {
        throw exception;
    }
}
