package com.example.banking.main.infrastructure.adapter.in.controllers.exception_handlers;

public class ExceptionDTO {
    public final String message;
    public final Integer status;

    public ExceptionDTO(String message, Integer status) {
        this.message = message;
        this.status = status;
    }
}
