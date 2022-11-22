package com.plutus.transactions.services;

import com.plutus.transactions.dtos.responses.ClientAccountsResponse;
import com.plutus.transactions.entities.Account;
import com.plutus.transactions.entities.Client;
import com.plutus.transactions.exceptions.RequestException;
import com.plutus.transactions.repositories.AccountRepository;
import com.plutus.transactions.repositories.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private AccountRepository accountRepository;
    private ClientRepository clientRepository;

    public AccountService(AccountRepository accountRepository, ClientRepository clientRepository) {
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
    }

    private void verifyClient(long clientId) {
        Optional<Client> client = this.clientRepository.findById(clientId);

        if (client.isEmpty()) {
            throw new RequestException(HttpStatus.NOT_FOUND.value(), "Client does not exist");
        }
    }
    public ClientAccountsResponse listClientAccounts(long clientId) {

        this.verifyClient(clientId); // First check whether it exists

        List<Account> accounts = this.accountRepository.findByClient_Id(clientId);
        return new ClientAccountsResponse(accounts);
    }

}
