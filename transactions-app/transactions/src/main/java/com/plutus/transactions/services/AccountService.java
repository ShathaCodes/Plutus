package com.plutus.transactions.services;

import com.plutus.transactions.dtos.responses.GenericResponse;
import com.plutus.transactions.dtos.responses.SuccessResponse;
import com.plutus.transactions.repositories.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private ClientRepository clientRepository;

    public AccountService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public GenericResponse listClientAccounts(long clientId) {
        return new SuccessResponse("Success");
    }

}
