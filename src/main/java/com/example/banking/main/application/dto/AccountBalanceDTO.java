package com.example.banking.main.application.dto;

public class AccountBalanceDTO {
    public final String id;
    public final Integer balance;

    public AccountBalanceDTO(String id, Integer balance) {
        this.id = id;
        this.balance = balance;
    }
}
