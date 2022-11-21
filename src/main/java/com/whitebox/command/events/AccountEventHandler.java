package com.whitebox.command.events;

import com.whitebox.command.controller.AccountCommandController;
import com.whitebox.command.entity.Account;
import com.whitebox.command.repository.AccountRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("account")
public class AccountEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountEventHandler.class);

    private AccountRepository accountRepository;

    public AccountEventHandler(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @EventHandler
    public void on(AccountCreatedEvent accountCreatedEvent) throws Exception {
        LOGGER.debug("AccountEventHandler:EventHandler");
        Account account = new Account();
        BeanUtils.copyProperties(accountCreatedEvent, account);
        accountRepository.save(account);
    }

    @ExceptionHandler
    public void handle(Exception exception) throws Exception {
        throw exception;
    }
}
