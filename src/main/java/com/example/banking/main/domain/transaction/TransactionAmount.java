package com.example.banking.main.domain.transaction;

public class TransactionAmount {
    private final String currency;
    private final Integer quantity;

    public TransactionAmount(String currency, Integer quantity) {
        this.currency = currency;
        this.quantity = quantity;
    }

    public static TransactionAmount Initial() {
        // @TODO future support more currencies
        return new TransactionAmount("AED", 0);
    }

    public static TransactionAmount Create(Integer quantity) {
        return new TransactionAmount("AED", quantity);
    }

    public TransactionAmount add(TransactionAmount transactionAmount) {
        return new TransactionAmount(currency, quantity + transactionAmount.quantity);
    }

    public TransactionAmount subtract(TransactionAmount transactionAmount) {
        return new TransactionAmount(currency, quantity - transactionAmount.quantity );
    }

    public Integer getQuantity() {
        return quantity;
    }

}
