package com.example.banking.main.application.dto;

import com.example.banking.main.domain.account.Account;

public class AccountDTO {
    public final String id;
    public final String name;

    public AccountDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static AccountDTO from(Account account) {
        String id = account.getIdentifier().getId();
        String name = account.getName().toString();
        return new AccountDTO(id, name);
    }
}
