package com.example.banking.main.infrastructure.adapter.out.in_memory;

import com.example.banking.main.application.port.out.TransactionStorage;
import com.example.banking.main.domain.transaction.Transaction;
import com.example.banking.main.domain.transaction.TransactionMother;
import com.example.banking.shared.domain.Identifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTransactionStorageTest {

    TransactionStorage transactionStorage;

    @BeforeEach
    void setup() {
        transactionStorage = new InMemoryTransactionStorage();
    }

    @Test
    public void shouldSaveTransaction() {
        Identifier accountId = Identifier.Create();
        Transaction transaction = TransactionMother.deposit(accountId);

        transactionStorage.save(transaction);
    }

    @Test
    public void shouldGetTransactionsByAccountId() {
        Identifier accountId = Identifier.Create();
        Transaction transaction = TransactionMother.deposit(accountId);

        transactionStorage.save(transaction);
        List<Transaction> transactions = transactionStorage.getByAccountId(accountId);

        assertEquals(transactions.size(), 1);
        Transaction result = transactions.stream().findFirst().get();
        assertEquals(result, transaction);
    }
}
