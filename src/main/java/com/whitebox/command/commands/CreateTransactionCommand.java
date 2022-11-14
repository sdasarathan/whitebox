package com.whitebox.command.commands;

import com.whitebox.model.TransactionType;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class CreateTransactionCommand {

    @TargetAggregateIdentifier
    private String id;
    private String accountId;
    private TransactionType transactionType;
    private BigDecimal amount;
    private Date transactionDate;
}
