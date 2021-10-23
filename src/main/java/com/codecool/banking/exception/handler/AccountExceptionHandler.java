package com.codecool.banking.exception.handler;

import com.codecool.banking.exception.exception.account.AccountCreationException;
import com.codecool.banking.exception.ExceptionDetails;
import com.codecool.banking.exception.exception.account.AccountNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class AccountExceptionHandler {

    @ExceptionHandler(AccountCreationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDetails accountCreationExceptionHandler(AccountCreationException exc, WebRequest request) {
        return ExceptionDetails.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(new Date())
                .message(exc.getMessage())
                .details(request.getDescription(false))
                .build();
    }

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDetails accountNotFoundExceptionHandler(AccountNotFoundException exc, WebRequest request) {
        return ExceptionDetails.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(new Date())
                .message(exc.getMessage())
                .details(request.getDescription(false))
                .build();
    }
}
