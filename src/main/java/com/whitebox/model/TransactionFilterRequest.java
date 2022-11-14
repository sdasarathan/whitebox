package com.whitebox.model;

import lombok.Data;

import java.util.Date;

@Data
public class TransactionFilterRequest {

    private Date dateFrom;
    private String accountId;
}
