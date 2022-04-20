package com.example.banking.main.infrastructure.adapter.in.controllers.exception_handlers;

import com.example.banking.main.domain.account.AccountDoesNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AccountDoesNotExistsExceptionHandler {

    @ExceptionHandler(value = AccountDoesNotExistException.class)
    public ResponseEntity<ExceptionDTO> exception(AccountDoesNotExistException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<ExceptionDTO>(exceptionDTO, HttpStatus.NOT_FOUND);
    }
}
