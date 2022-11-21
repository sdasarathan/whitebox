package com.whitebox.command.controller;

import com.whitebox.command.aggregate.AccountAggregate;
import com.whitebox.command.commands.CreateAccountCommand;
import com.whitebox.command.commands.CreateTransactionCommand;
import com.whitebox.command.entity.Transaction;
import com.whitebox.model.AccountDto;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class AccountCommandController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountCommandController.class);
    public AccountCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    private CommandGateway commandGateway;

    @PostMapping("/account")
    public String createAccount(@RequestBody AccountDto account){
        LOGGER.debug("In AccountCommandController:createAccount");
        CreateAccountCommand createAccountCommand = CreateAccountCommand.builder()
                .id(UUID.randomUUID().toString())
                .holderName(account.getHolderName())
                .balance(account.getBalance())
                .build();
        String result = commandGateway.sendAndWait(createAccountCommand);
        return result;
    }

    @PostMapping("/transaction")
    public String transaction(@RequestBody Transaction transaction){
        LOGGER.debug("In AccountCommandController:createAccount");
        CreateTransactionCommand transactionCommand = CreateTransactionCommand.builder()
                .id(UUID.randomUUID().toString())
                .accountId(transaction.getAccountId())
                .amount(transaction.getAmount())
                .transactionType(transaction.getTransactionType())
                .transactionDate(new Date())
                .build();
        String result = commandGateway.sendAndWait(transactionCommand);
        return result;
    }
}
