package com.whitebox.command.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class Account {

    @Id
    private String id;
    private String holderName;
    private BigDecimal balance;
}
