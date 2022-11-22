package com.plutus.transactions.dtos.responses;

import com.plutus.transactions.entities.ExchangeTransaction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExchangeTransactionResponse extends TransactionResponse {

    private long senderId;
    private long receiverId;

    public ExchangeTransactionResponse(ExchangeTransaction transaction) {
        super(transaction.getId(), transaction.getTimestamp(), transaction.getAmount());
        this.senderId = transaction.getSender().getId();
        this.receiverId = transaction.getReceiver().getId();
    }
}
