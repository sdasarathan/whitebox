package com.whitebox.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class TransactionDto {
    private String id;
    private String accountId;
    private BigDecimal amount;
    private TransactionType transactionType;
    private Date transactionDate;
}
