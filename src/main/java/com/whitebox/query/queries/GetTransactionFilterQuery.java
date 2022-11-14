package com.whitebox.query.queries;

import lombok.Data;

import java.util.Date;

@Data
public class GetTransactionFilterQuery {

    private Date dateFrom;
    private String accountId;
}
