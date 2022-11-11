package com.whitebox.command.commands;

import com.whitebox.model.TransactionType;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
@Builder
public class CreateTransactionCommand {

    @TargetAggregateIdentifier
    private String id;
    private String accountId;
    private TransactionType transactionType;
    private BigDecimal amount;
}
