package com.whitebox.command.controller;

import com.whitebox.command.commands.CreateAccountCommand;
import com.whitebox.model.AccountDto;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
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
//                .accountId(UUID.randomUUID().toString())
                .id("1")
                .holderName("Test")
                .balance(BigDecimal.valueOf(0))
                .build();
        String result = commandGateway.sendAndWait(createAccountCommand);
        return result;
    }
}
