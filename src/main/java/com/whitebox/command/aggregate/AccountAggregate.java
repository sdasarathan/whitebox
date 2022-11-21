package com.whitebox.command.aggregate;

import com.whitebox.command.commands.CreateAccountCommand;
import com.whitebox.command.events.AccountCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class AccountAggregate {

    @AggregateIdentifier
    private String id;
    private String holderName;
    private BigDecimal balance;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountAggregate.class);

    public AccountAggregate() {
    }

    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand) {
        LOGGER.debug("Aggregate:CommandHandler");
        AccountCreatedEvent accountCreatedEvent = new AccountCreatedEvent();
        BeanUtils.copyProperties(createAccountCommand,accountCreatedEvent);

        //Publishing the event to event store
        AggregateLifecycle.apply(accountCreatedEvent);
    }

    @EventHandler
    public void on(AccountCreatedEvent accountCreatedEvent ) {
        LOGGER.debug("Aggregate:EventHandler");
        this.id = accountCreatedEvent.getId();
        this.balance = accountCreatedEvent.getBalance();
        this.holderName = accountCreatedEvent.getHolderName();
    }
}
