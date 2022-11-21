package com.plutus.transactions.services;

import com.plutus.transactions.dtos.responses.AccountTransactionResponse;
import com.plutus.transactions.dtos.responses.AppResponse;
import com.plutus.transactions.dtos.responses.ErrorResponse;
import com.plutus.transactions.dtos.responses.GenericResponse;
import com.plutus.transactions.entities.Account;
import com.plutus.transactions.entities.AccountTransaction;
import com.plutus.transactions.entities.ExchangeTransaction;
import com.plutus.transactions.entities.Transaction;
import com.plutus.transactions.entities.enumerations.AccountTransactionType;
import com.plutus.transactions.repositories.AccountRepository;
import com.plutus.transactions.repositories.AccountTransactionRepository;
import com.plutus.transactions.repositories.ClientRepository;
import com.plutus.transactions.repositories.ExchangeTransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

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

    private ExchangeTransaction generateExchangeTransaction(Account sender, Account receiver, double amount) {
        ExchangeTransaction transaction = new ExchangeTransaction();
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setAmount(amount);
        transaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return this.exchangeTransactionRepository.save(transaction);
    }
    private AccountTransaction generateAccountTransaction(Account account, double amount, AccountTransactionType type) {
        AccountTransaction transaction = new AccountTransaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setType(type);
        transaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return this.accountTransactionRepository.save(transaction);
    }

    private ErrorResponse verifyAmount(double amount) {
        if (amount <= 0) {
            return new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid amount passed");
        }
        return null;
    }

    public AppResponse withdraw(long accountId, double balance ) {

        ErrorResponse error = verifyAmount(balance);
        if (error != null) {
            return error;
        }

        Optional<Account> account = this.accountRepository.findById(accountId);

        if (account.isEmpty()) {
            return new ErrorResponse(HttpStatus.BAD_REQUEST, "Account does not exist");
        }

        Account existingAccount = account.get();
        double newBalance = existingAccount.getBalance() - balance;

        if (newBalance < 0) {
            return new ErrorResponse(HttpStatus.BAD_REQUEST, "Insufficient account balance");
        }
        existingAccount.setBalance(newBalance);

        existingAccount = this.accountRepository.save(existingAccount);

        AccountTransaction transaction = generateAccountTransaction(existingAccount, balance, AccountTransactionType.WITHDRAW);
        return new AccountTransactionResponse(transaction);
    }

    public AppResponse deposit(long accountId, double balance) {

        ErrorResponse error = verifyAmount(balance);
        if (error != null) {
            return error;
        }

        Optional<Account> account = this.accountRepository.findById(accountId);

        if (account.isEmpty()) {
            return new ErrorResponse(HttpStatus.BAD_REQUEST, "Account does not exist");
        }

        Account existingAccount = account.get();
        double newBalance = existingAccount.getBalance() + balance;

        existingAccount.setBalance(newBalance);

        existingAccount = this.accountRepository.save(existingAccount);

        AccountTransaction transaction = generateAccountTransaction(existingAccount, balance, AccountTransactionType.WITHDRAW);
        return new AccountTransactionResponse(transaction);
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
