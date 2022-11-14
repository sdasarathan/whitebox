package com.whitebox.command.aggregate;

import com.whitebox.command.commands.CreateTransactionCommand;
import com.whitebox.command.events.AccountCreatedEvent;
import com.whitebox.command.events.TransactionCreatedEvent;
import com.whitebox.model.TransactionType;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

@Aggregate
public class TransactionAggregate {

    @AggregateIdentifier
    private String id;
    private TransactionType transactionType;
    private BigDecimal amount;

    private Date transactionDate;


    public TransactionAggregate() {
    }

    @CommandHandler
    public TransactionAggregate(CreateTransactionCommand transactionCommand) {
        System.out.println("Transaction Aggregate:CommandHandler");
        TransactionCreatedEvent transactionCreatedEvent = new TransactionCreatedEvent();
        BeanUtils.copyProperties(transactionCommand,transactionCreatedEvent);

        //Publishing the event to event store
        AggregateLifecycle.apply(transactionCreatedEvent);
    }

    @EventHandler
    public void on(TransactionCreatedEvent transactionCreatedEvent ) {
        System.out.println("Transaction Aggregate:EventHandler");
        this.id = transactionCreatedEvent.getId();
        this.transactionType = transactionCreatedEvent.getTransactionType();
        this.amount = transactionCreatedEvent.getAmount();
        this.transactionDate = transactionCreatedEvent.getTransactionDate();
    }
}
