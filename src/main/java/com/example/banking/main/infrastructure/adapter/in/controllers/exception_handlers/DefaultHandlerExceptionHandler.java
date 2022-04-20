package com.example.banking.main.infrastructure.adapter.in.controllers.exception_handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultHandlerExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDTO> exception(MethodArgumentNotValidException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<ExceptionDTO>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }
}
