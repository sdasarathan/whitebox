package com.whitebox.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class AccountDto {

    @NonNull
    private String holderName;

    @NonNull
    private BigDecimal balance;
}
