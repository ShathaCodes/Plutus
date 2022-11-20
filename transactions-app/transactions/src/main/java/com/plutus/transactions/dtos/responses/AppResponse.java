package com.plutus.transactions.dtos.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class AppResponse implements GenericResponse{
    private String message;

    public AppResponse(String message) {
        this.message = message;
    }
}
