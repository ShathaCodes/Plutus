package com.plutus.transactions.services;

import com.plutus.transactions.dtos.responses.AccountTransactionResponse;
import com.plutus.transactions.dtos.responses.ExchangeTransactionResponse;
import com.plutus.transactions.dtos.responses.GenericResponse;
import com.plutus.transactions.dtos.responses.SuccessResponse;
import com.plutus.transactions.entities.Account;
import com.plutus.transactions.entities.AccountTransaction;
import com.plutus.transactions.entities.ExchangeTransaction;
import com.plutus.transactions.entities.enumerations.AccountTransactionType;
import com.plutus.transactions.exceptions.RequestException;
import com.plutus.transactions.repositories.AccountRepository;
import com.plutus.transactions.repositories.AccountTransactionRepository;
import com.plutus.transactions.repositories.ClientRepository;
import com.plutus.transactions.repositories.ExchangeTransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    /**
     * Generates a transaction between two parties and saves it in db.
     * @param sender
     * @param receiver
     * @param amount
     * @return
     */
    private ExchangeTransaction generateExchangeTransaction(Account sender, Account receiver, double amount) {
        ExchangeTransaction transaction = new ExchangeTransaction();
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setAmount(amount);
        transaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return this.exchangeTransactionRepository.save(transaction);
    }

    /**
     * Generates an account transaction and saves it in db.
     * @param account
     * @param amount
     * @param type
     * @return
     */
    private AccountTransaction generateAccountTransaction(Account account, double amount, AccountTransactionType type) {
        AccountTransaction transaction = new AccountTransaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setType(type);

        transaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return this.accountTransactionRepository.save(transaction);
    }

    /**
     * Verifies that a passed amount is valid.
     * @param amount
     */
    private void verifyAmount(double amount) {
        if (amount <= 0) {
            throw new RequestException(HttpStatus.BAD_REQUEST.value(), "Invalid amount passed");
        }
    }

    /**
     * Finds an account and returns it.
     * @param accountId
     * @return
     */
    private Account findAccount(long accountId) {
        Optional<Account> account = this.accountRepository.findById(accountId);

        if (account.isEmpty()) {
            throw new RequestException(HttpStatus.NOT_FOUND.value(), "Account does not exist");
        }
        return account.get();
    }

    /**
     * Withdraw an amount from an account and returns the transaction details.
     * @param accountId
     * @param amount
     * @return
     */
    public AccountTransactionResponse withdraw(long accountId, double amount ) {

        verifyAmount(amount);

        Account account = findAccount(accountId);

        double newBalance = account.getBalance() - amount;

        if (newBalance < 0) {
            throw new RequestException(HttpStatus.BAD_REQUEST.value(), "Insufficient Funds");
        }
        account.setBalance(newBalance);

        account = this.accountRepository.save(account);

        AccountTransaction transaction = generateAccountTransaction(account, amount, AccountTransactionType.WITHDRAW);
        return new AccountTransactionResponse(transaction);
    }

    /**
     * deposits an amount to an account and returns the transaction details.
     * @param accountId
     * @param amount
     * @return
     */
    public AccountTransactionResponse deposit(long accountId, double amount) {

        verifyAmount(amount);
        Account account = findAccount(accountId);

        double newBalance = account.getBalance() + amount;

        account.setBalance(newBalance);

        account = this.accountRepository.save(account);

        AccountTransaction transaction = generateAccountTransaction(account, amount, AccountTransactionType.DEPOSIT);
        return new AccountTransactionResponse(transaction);
    }

    /**
     * Sends funds from one account to another
     * @param senderId
     * @param receiverId
     * @param amount
     * @return
     */
    public ExchangeTransactionResponse send(long senderId, long receiverId, double amount) {
        verifyAmount(amount);
        Account sender = findAccount(senderId);
        Account receiver = findAccount(receiverId);

        Account[] accountsList = { sender, receiver };
        List<Account> accounts = new ArrayList<>(Arrays.asList(accountsList));

        double newBalanceSender = sender.getBalance() - amount;

        if (newBalanceSender < 0) {
            throw new RequestException(HttpStatus.BAD_REQUEST.value(), "Insufficient Funds");
        }

        double newBalanceReceiver = receiver.getBalance() + amount;
        accounts.get(0).setBalance(newBalanceSender);
        accounts.get(1).setBalance(newBalanceReceiver);

        accounts = this.accountRepository.saveAll(accounts); // All Or Nothing!

        ExchangeTransaction transaction = generateExchangeTransaction(accounts.get(0), accounts.get(1), amount);
        return new ExchangeTransactionResponse(transaction);
    }

    public GenericResponse listAccountTransactions(long accountId) {
        return new SuccessResponse("Success");
    }

    public GenericResponse listClientTransactions(long clientId) {
        return new SuccessResponse("Success");
    }

}
