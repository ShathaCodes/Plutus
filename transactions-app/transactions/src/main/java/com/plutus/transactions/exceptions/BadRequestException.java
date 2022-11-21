package com.plutus.transactions.exceptions;

public class BadRequestException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public int errCode ;

    public BadRequestException(int errCode, String msg) {
        super(msg);
        this.errCode = errCode ;
    }
}