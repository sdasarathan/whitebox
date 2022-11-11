package com.whitebox.command.aggregate;

import com.whitebox.command.commands.CreateAccountCommand;
import com.whitebox.command.events.AccountCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class AccountAggregate {

    @AggregateIdentifier
    private String id;
    private String holderName;
    private BigDecimal balance;

    public AccountAggregate() {
    }

    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand) {
        System.out.println("Aggregate:CommandHandler");
        AccountCreatedEvent accountCreatedEvent = new AccountCreatedEvent();
        BeanUtils.copyProperties(createAccountCommand,accountCreatedEvent);

        //Publishing the event to event store
        AggregateLifecycle.apply(accountCreatedEvent);
    }

    @EventHandler
    public void on(AccountCreatedEvent accountCreatedEvent ) {
        System.out.println("Aggregate:EventHandler");
        this.id = accountCreatedEvent.getId();
        this.balance = accountCreatedEvent.getBalance();
        this.holderName = accountCreatedEvent.getHolderName();
    }
}
