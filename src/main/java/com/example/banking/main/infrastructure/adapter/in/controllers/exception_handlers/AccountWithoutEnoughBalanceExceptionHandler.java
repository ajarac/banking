package com.example.banking.main.infrastructure.adapter.in.controllers.exception_handlers;

import com.example.banking.main.domain.account.AccountWithoutEnoughBalanceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AccountWithoutEnoughBalanceExceptionHandler {
    @ExceptionHandler(value = AccountWithoutEnoughBalanceException.class)
    public ResponseEntity<ExceptionDTO> exception(AccountWithoutEnoughBalanceException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.CONFLICT);
    }
}
