package com.plutus.transactions.dtos.responses;

import com.plutus.transactions.entities.AccountTransaction;
import com.plutus.transactions.entities.ExchangeTransaction;
import com.plutus.transactions.entities.Transaction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor
public class AccountTransactionsResponse {

    private List<AccountTransaction> accountTransactions;
    private List<ExchangeTransaction> exchangeTransactions;
    public AccountTransactionsResponse(List<AccountTransaction> accountTransactions, List<ExchangeTransaction> exchangeTransactions) {
        this.accountTransactions =  accountTransactions;
        this.exchangeTransactions = exchangeTransactions;
    }
}
