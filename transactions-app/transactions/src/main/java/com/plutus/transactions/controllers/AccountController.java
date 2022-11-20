package com.plutus.transactions.controllers;

import com.plutus.transactions.dtos.responses.GenericResponse;
import com.plutus.transactions.services.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(path = "/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/clients/{clientId}")
    public GenericResponse listClientAccount(@PathVariable long clientId) {
        return this.accountService.listClientAccounts(clientId);
    }
}
