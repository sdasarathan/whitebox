package com.whitebox.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionDto {
    private String id;
    private String accountId;
    private BigDecimal amount;
    private TransactionType transaction;
}
