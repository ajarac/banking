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
public class MakeDepositTransactionUseCase {

    private final TransactionStorage transactionStorage;
    private final AccountStorage accountStorage;

    public MakeDepositTransactionUseCase(TransactionStorage transactionStorage, AccountStorage accountStorage) {
        this.transactionStorage = transactionStorage;
        this.accountStorage = accountStorage;
    }

    public void invoke(Identifier to, TransactionAmount amount) throws AccountDoesNotExistException {
        Optional<Account> optionalAccount = accountStorage.getById(to);
        if (optionalAccount.isEmpty()) {
            throw new AccountDoesNotExistException(to);
        }

        Transaction transaction = Transaction.Deposit(to, amount);

        transactionStorage.save(transaction);
    }

}
