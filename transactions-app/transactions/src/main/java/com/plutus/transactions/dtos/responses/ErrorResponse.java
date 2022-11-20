package com.plutus.transactions.dtos.responses;

import org.springframework.http.HttpStatus;

public class ErrorResponse extends AppResponse {

    private HttpStatus status;

    public ErrorResponse(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
