package com.example.banking.main.domain.transaction;

import com.example.banking.shared.domain.Identifier;

public class TransactionMother {
    public static Transaction deposit(Identifier to) {
        return Transaction.Deposit(to, TransactionAmount.Create(50));
    }
}
