package com.example.banking.main.domain.account;

import com.example.banking.shared.domain.Identifier;

public class Account {

    private Identifier id;
    private AccountName name;

    private Account(Identifier id, AccountName name) {
        this.id = id;
        this.name = name;
    }

    public static Account Create(AccountName accountName) {
        return new Account(Identifier.Create(), accountName);
    }

    public Identifier getIdentifier() {
        return id;
    }

    public AccountName getName() {
        return name;
    }
}
