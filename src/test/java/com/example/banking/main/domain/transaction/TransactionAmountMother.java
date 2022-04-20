package com.example.banking.main.domain.transaction;

public class TransactionAmountMother {
    public static TransactionAmount random() {
        return TransactionAmount.Create(50);
    }
}
