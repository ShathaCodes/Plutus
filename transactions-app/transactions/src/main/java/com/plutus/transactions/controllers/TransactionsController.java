package com.plutus.transactions.controllers;

import com.plutus.transactions.dtos.requests.AccountActionRequest;
import com.plutus.transactions.dtos.requests.ExchangeRequest;
import com.plutus.transactions.dtos.responses.AccountTransactionResponse;
import com.plutus.transactions.dtos.responses.AccountTransactionsResponse;
import com.plutus.transactions.dtos.responses.GenericResponse;
import com.plutus.transactions.dtos.responses.SuccessResponse;
import com.plutus.transactions.exceptions.RequestException;
import com.plutus.transactions.services.TransactionsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController()
@RequestMapping(path = "/transactions")
public class TransactionsController {

    private TransactionsService transactionsService;

    public TransactionsController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @PutMapping("/withdraw")
    public AccountTransactionResponse withdraw(@RequestBody AccountActionRequest withdrawBody) {
        return this.transactionsService.withdraw(withdrawBody.getAccountId(), withdrawBody.getAmount());
    }

    @PutMapping("/deposit")
    public AccountTransactionResponse deposit(@RequestBody AccountActionRequest depositBody) {
        return this.transactionsService.deposit(depositBody.getAccountId(), depositBody.getAmount());
    }

    @PutMapping("/send")
    public SuccessResponse send(@RequestBody ExchangeRequest exchangeBody) {
        return this.transactionsService.send(exchangeBody.getSenderId(), exchangeBody.getReceiverId(), exchangeBody.getAmount());
    }

    @GetMapping("/accounts/{accountId}")
    public AccountTransactionsResponse listAccountTransactions(@PathVariable long accountId) {
        return  this.transactionsService.listAccountTransactions(accountId);
    }

    @ExceptionHandler(RequestException.class)
    void handleBadRequests(RequestException exception, HttpServletResponse response) throws IOException {
        response.sendError(exception.errCode, String.format("[Transactions] : %s", exception.getMessage()));
    }

}
