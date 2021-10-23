package com.codecool.banking.exception.exception.account;

public class AccountCreationException extends RuntimeException {
    public AccountCreationException() {
    }

    public AccountCreationException(String message) {
        super(message);
    }

    public AccountCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountCreationException(Throwable cause) {
        super(cause);
    }
}
