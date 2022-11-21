package com.plutus.transactions.dtos.responses;

public class TransactionResponse extends AppResponse {

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
