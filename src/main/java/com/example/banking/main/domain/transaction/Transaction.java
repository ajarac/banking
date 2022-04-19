package com.example.banking.main.domain.transaction;

import com.example.banking.shared.domain.Identifier;

public class Transaction {
    private Identifier id;
    private Identifier from;
    private Identifier to;
    private TransactionAmount transactionAmount;
    private TransactionType type;

    private Transaction(Identifier id, Identifier from, Identifier to, TransactionAmount transactionAmount, TransactionType type) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.transactionAmount = transactionAmount;
        this.type = type;
    }

    public static Transaction Withdrawal(Identifier from, TransactionAmount transactionAmount) {
        return new Transaction(Identifier.Create(), from, null, transactionAmount, TransactionType.WITHDRAWAL);
    }

    public static Transaction Deposit(Identifier to, TransactionAmount transactionAmount) {
        return new Transaction(Identifier.Create(), null, to, transactionAmount, TransactionType.DEPOSIT);
    }

    public static Transaction Local(Identifier from, Identifier to, TransactionAmount transactionAmount) {
        return new Transaction(Identifier.Create(), from, to, transactionAmount, TransactionType.LOCAL);
    }

    public static Transaction International(Identifier from, Identifier to, TransactionAmount transactionAmount) {
        return new Transaction(Identifier.Create(), from, to, transactionAmount, TransactionType.INTERNATIONAL);
    }

    public TransactionType getType() {
        return type;
    }

    public Identifier getFrom() {
        return from;
    }

    public Identifier getTo() {
        return to;
    }
}
