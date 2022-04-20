package com.example.banking.main.application.port.in;

import com.example.banking.main.application.port.out.AccountStorage;
import com.example.banking.main.application.port.out.TransactionStorage;
import com.example.banking.main.domain.account.Account;
import com.example.banking.main.domain.account.AccountDoesNotExistException;
import com.example.banking.main.domain.transaction.Transaction;
import com.example.banking.main.domain.transaction.TransactionAmount;
import com.example.banking.shared.domain.Identifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MakeLocalTransactionUseCase {

    private final TransactionStorage transactionStorage;
    private final AccountStorage accountStorage;

    public MakeLocalTransactionUseCase(TransactionStorage transactionStorage, AccountStorage accountStorage
    ) {
        this.transactionStorage = transactionStorage;
        this.accountStorage = accountStorage;
    }

    public void invoke(Identifier from, Identifier to, TransactionAmount amount) throws AccountDoesNotExistException {
        Optional<Account> optionalAccountFrom = accountStorage.getById(from);
        Optional<Account> optionalAccountTo = accountStorage.getById(to);
        if (optionalAccountFrom.isEmpty()) {
            throw new AccountDoesNotExistException(from);
        }
        if (optionalAccountTo.isEmpty()) {
            throw new AccountDoesNotExistException(to);
        }

        Transaction transaction = Transaction.Local(from, to, amount);

        transactionStorage.createTransaction(transaction);
    }
}