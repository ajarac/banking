package com.example.banking.main.application.port.out;

import com.example.banking.main.domain.transaction.Transaction;
import com.example.banking.shared.domain.Identifier;

import java.util.List;

public interface TransactionStorage {
    void save(Transaction transaction);

    List<Transaction> getByAccountId(Identifier accountId);
}
