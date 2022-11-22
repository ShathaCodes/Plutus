package com.plutus.transactions.dtos.responses;

import com.plutus.transactions.entities.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor
public class ClientAccountsResponse {

    private List<Account> accounts;

    public ClientAccountsResponse(List<Account> accounts) {
        this.accounts = accounts;
    }
}
