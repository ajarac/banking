package com.example.banking.main.domain.transaction;

public class TransactionAmountMother {
    public static TransactionAmount random() {
        return TransactionAmount.Create(50);
    }

    public static TransactionAmount of(Integer quantity) {
        return TransactionAmount.Create(quantity);
    }
}
