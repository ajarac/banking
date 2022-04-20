package com.example.banking.main.domain;

import com.example.banking.main.domain.transaction.Transaction;
import com.example.banking.shared.domain.Identifier;

import java.util.List;

public class CalculateBalanceService {
    public static Integer calculateBalance(List<Transaction> transactions, Identifier accountId) {
        Integer balance = 0;
        for (Transaction transaction : transactions) {
            Integer amount = transaction.getTransactionAmount().getQuantity();
            balance += calculateNewBalance(accountId, transaction, amount);
        }
        return balance;
    }

    private static Integer calculateNewBalance(Identifier accountId, Transaction transaction, Integer amount) {

        switch (transaction.getType()) {
            case INTERNATIONAL:
            case WITHDRAWAL:
                return -amount;
            case DEPOSIT:
                return +amount;
            case LOCAL:
                if (transaction.getFrom().equals(accountId)) {
                    return -amount;
                } else {
                    return +amount;
                }
            default:
                return 0;
        }
    }
}
