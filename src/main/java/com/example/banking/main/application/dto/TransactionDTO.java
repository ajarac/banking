package com.example.banking.main.application.dto;

public class TransactionDTO {
    public final String id;
    public final String from;
    public final String to;
    public final Integer amount;
    public final String type;

    public TransactionDTO(String id, String from, String to, Integer amount, String type) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.type = type;
    }
}
