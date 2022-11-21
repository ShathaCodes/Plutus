package com.plutus.transactions.controllers;

import com.plutus.transactions.dtos.responses.GenericResponse;
import com.plutus.transactions.exceptions.BadRequestException;
import com.plutus.transactions.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
