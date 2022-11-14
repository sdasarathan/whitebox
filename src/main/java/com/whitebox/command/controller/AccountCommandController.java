package com.whitebox.command.controller;

import com.whitebox.command.commands.CreateAccountCommand;
import com.whitebox.command.commands.CreateTransactionCommand;
import com.whitebox.command.entity.Transaction;
import com.whitebox.model.AccountDto;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountCommandController {

    public AccountCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    private CommandGateway commandGateway;

    @PostMapping("/create")
    public String createAccount(@RequestBody AccountDto account){
        System.out.println("In AccountCommandController:createAccount");
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
        System.out.println("In AccountCommandController:createAccount");
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
