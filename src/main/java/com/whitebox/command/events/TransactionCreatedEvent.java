package com.whitebox.command.events;

import com.whitebox.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCreatedEvent {

    private String id;
    private String accountId;
    private BigDecimal amount;
    private TransactionType transactionType;
}
