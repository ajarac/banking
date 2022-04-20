package com.example.banking.main.domain.account;

import com.example.banking.shared.domain.Identifier;

public class AccountWithoutEnoughBalanceException extends Exception {
    public AccountWithoutEnoughBalanceException(Identifier identifier) {
        super("Account with id " + identifier.toString() + " has not enough balance");
    }
}
