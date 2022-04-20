package com.example.banking.main.infrastructure.adapter.out.in_memory;

import com.example.banking.main.application.port.out.TransactionStorage;
import com.example.banking.main.domain.transaction.Transaction;
import com.example.banking.shared.domain.Identifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class InMemoryTransactionStorage implements TransactionStorage {
    private final HashMap<String, ArrayList<Transaction>> map = new HashMap<>();

    private static List<String> getAccountsToUpdate(Transaction transaction) {
        switch (transaction.getType()) {
            case WITHDRAWAL:
            case INTERNATIONAL:
                return List.of(transaction.getFrom().toString());
            case DEPOSIT:
                return List.of(transaction.getTo().toString());
            case LOCAL:
                return List.of(transaction.getTo().toString(), transaction.getFrom().toString());
        }
        return List.of();
    }

    @Override
    public void save(Transaction transaction) {
        List<String> accountsToUpdate = getAccountsToUpdate(transaction);
        for (String accountId : accountsToUpdate) {
            ArrayList<Transaction> transactions = map.getOrDefault(accountId, new ArrayList<>());
            transactions.add(transaction);
            map.put(accountId, transactions);
        }
    }

    @Override
    public List<Transaction> getByAccountId(Identifier accountId) {
        return map.getOrDefault(accountId.toString(), new ArrayList<>());
    }
}
