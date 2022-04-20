package com.example.banking.main.domain.account;

import com.example.banking.shared.domain.Identifier;

public class AccountDoesNotExistException extends Exception {

    public AccountDoesNotExistException(Identifier identifier) {
        super("Account with id: " + identifier.getId() + " does not exist");
    }
}
