package com.plutus.transactions.dtos.responses;

import com.plutus.transactions.entities.ExchangeTransaction;
import com.plutus.transactions.entities.Transaction;

public class ExchangeTransactionResponse extends TransactionResponse {

    private long senderId;
    private long receiverId;

    public ExchangeTransactionResponse(ExchangeTransaction transaction) {
        super(transaction.getId(), transaction.getTimestamp().toString(), transaction.getAmount());
        this.senderId = transaction.getSender().getId();
        this.receiverId = transaction.getReceiver().getId();
    }
}
