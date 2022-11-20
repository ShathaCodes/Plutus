package com.plutus.transactions.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AccountActionRequest {
    private double amount;
    private long accountId;
}
