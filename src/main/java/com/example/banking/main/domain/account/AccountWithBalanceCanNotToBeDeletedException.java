package com.example.banking.main.domain.account;

import com.example.banking.shared.domain.Identifier;

public class AccountWithBalanceCanNotToBeDeletedException extends Exception {
    public AccountWithBalanceCanNotToBeDeletedException(Identifier identifier) {
        super("Account with id " + identifier.getId() + " needs to do not have balance to be deleted");
    }
}
