package com.bank.bank_app.shared.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String error){
        super(error);
    }
}
