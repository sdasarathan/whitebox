package com.whitebox.command.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreatedEvent {

    private String id;
    private String holderName;
    private BigDecimal balance;
}
