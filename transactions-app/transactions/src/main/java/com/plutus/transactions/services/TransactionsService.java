package com.plutus.transactions.services;

import com.plutus.transactions.dtos.responses.AppResponse;
import com.plutus.transactions.dtos.responses.GenericResponse;
import com.plutus.transactions.entities.Account;
import com.plutus.transactions.repositories.AccountRepository;
import com.plutus.transactions.repositories.AccountTransactionRepository;
import com.plutus.transactions.repositories.ClientRepository;
import com.plutus.transactions.repositories.ExchangeTransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionsService {

    private AccountTransactionRepository accountTransactionRepository;
    private ExchangeTransactionRepository exchangeTransactionRepository;
    private ClientRepository clientRepository;
    private AccountRepository accountRepository;

    public TransactionsService(AccountTransactionRepository accountTransactionRepository,
                                  ExchangeTransactionRepository exchangeTransactionRepository,
                                  ClientRepository clientRepository,
                                  AccountRepository accountRepository
    ) {
        this.accountTransactionRepository = accountTransactionRepository;
        this.exchangeTransactionRepository = exchangeTransactionRepository;
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }

    public AppResponse withdraw(long accountId, double balance ) {
        return new AppResponse("Withdraw successful");
    }

    public AppResponse deposit(long accountId, double balance) {
        return new AppResponse("Deposit successful");
    }

    public AppResponse send(long senderId, long receiverId, double amount) {
        return new AppResponse("Send successful");
    }

    public GenericResponse listAccountTransactions(long accountId) {
        return new AppResponse("Success");
    }

    public GenericResponse listClientTransactions(long clientId) {
        return new AppResponse("Success");
    }

}
