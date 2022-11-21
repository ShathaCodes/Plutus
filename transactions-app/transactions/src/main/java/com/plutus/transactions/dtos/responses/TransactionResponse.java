package com.plutus.transactions.dtos.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransactionResponse extends SuccessResponse {

    private long transactionId;
    private String timestamp ;
    private double amount;

    public TransactionResponse(long transactionId, String timestamp, double amount) {
        super("Transaction successful");
        this.transactionId = transactionId;
        this.timestamp = timestamp;
        this.amount = amount;
    }
}
