package com.plutus.transactions.dtos.responses;

import com.plutus.transactions.entities.AccountTransaction;
import com.plutus.transactions.entities.enumerations.AccountTransactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountTransactionResponse extends TransactionResponse{

    private AccountTransactionType type;

    public AccountTransactionResponse(AccountTransaction transaction) {
        super(transaction.getId(), transaction.getTimestamp(), transaction.getAmount());
        this.type = transaction.getType();
    }
}
