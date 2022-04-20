package com.example.banking.main.domain.transaction;

import com.example.banking.shared.domain.Identifier;

import java.util.List;

public class TransactionMother {
    public static Transaction deposit(Identifier to) {
        return Transaction.Deposit(to, TransactionAmountMother.random());
    }

    public static List<Transaction> listDeposit(Identifier to) {
        return List.of(deposit(to));
    }
}
