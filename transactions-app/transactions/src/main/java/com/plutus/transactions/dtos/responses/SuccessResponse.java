package com.plutus.transactions.dtos.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class SuccessResponse implements GenericResponse{
    private String message;

    public SuccessResponse(String message) {
        this.message = message;
    }
}
