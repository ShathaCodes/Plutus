package com.plutus.transactions.controllers;

import com.plutus.transactions.dtos.requests.AccountActionRequest;
import com.plutus.transactions.dtos.requests.ExchangeRequest;
import com.plutus.transactions.dtos.responses.AppResponse;
import com.plutus.transactions.dtos.responses.GenericResponse;
import com.plutus.transactions.services.TransactionsService;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping(path = "/transactions")
public class TransactionsController {

    private TransactionsService transactionsService;

    public TransactionsController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @PutMapping("/withdraw")
    public AppResponse withdraw(@RequestBody AccountActionRequest withdrawBody) {
        return this.transactionsService.withdraw(withdrawBody.getAccountId(), withdrawBody.getAmount());
    }

    @PutMapping("/deposit")
    public AppResponse deposit(@RequestBody AccountActionRequest depositBody) {
        return this.transactionsService.deposit(depositBody.getAccountId(), depositBody.getAmount());
    }

    @PutMapping("/send")
    public AppResponse send(@RequestBody ExchangeRequest exchangeBody) {
        return this.transactionsService.send(exchangeBody.getSenderId(), exchangeBody.getReceiverId(), exchangeBody.getAmount());
    }

    @GetMapping("/accounts/{accountId}")
    public GenericResponse listAccountTransactions(@PathVariable long accountId) {
        return  this.transactionsService.listAccountTransactions(accountId);
    }

    @GetMapping("/clients/{clientId}")
    public GenericResponse listClientTransactions(@PathVariable long clientId) {
        return  this.transactionsService.listAccountTransactions(clientId);
    }


}
