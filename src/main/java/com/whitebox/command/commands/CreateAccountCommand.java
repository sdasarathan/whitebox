package com.whitebox.command.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
@Builder
public class CreateAccountCommand {

    @TargetAggregateIdentifier
    private String id;
    private String holderName;
    private BigDecimal balance;
}
