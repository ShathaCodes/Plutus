package com.plutus.transactions.dtos.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ExchangeRequest {
    private long senderId;
    private long receiverId;
    private double amount;
}
