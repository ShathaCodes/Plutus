package com.plutus.transactions.dtos.responses;

import com.plutus.transactions.entities.AccountTransaction;
import com.plutus.transactions.entities.enumerations.AccountTransactionType;

public class AccountTransactionResponse extends TransactionResponse{

    private AccountTransactionType type;

    public AccountTransactionResponse(AccountTransaction transaction) {
        super(transaction.getId(), transaction.getTimestamp().toString(), transaction.getAmount());
        this.type = transaction.getType();
    }
}
