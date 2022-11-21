package com.plutus.transactions.exceptions;

public class RequestException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public int errCode ;

    public RequestException(int errCode, String msg) {
        super(msg);
        this.errCode = errCode ;
    }
}