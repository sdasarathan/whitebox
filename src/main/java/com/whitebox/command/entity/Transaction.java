package com.whitebox.command.entity;

import com.whitebox.model.TransactionType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class Transaction {

    @Id
    private String id;
    private String accountId;
    private BigDecimal amount;
    private TransactionType transactionType;
}
