package com.whitebox.model;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class AccountDto {


    private String id;

    @NonNull
    private String holderName;

    @NonNull
    private BigDecimal balance;
}
