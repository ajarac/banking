package com.example.banking.main.application.port.in;

import com.example.banking.main.application.port.out.AccountStorage;
import com.example.banking.main.application.port.out.TransactionStorage;
import com.example.banking.main.domain.CalculateBalanceService;
import com.example.banking.main.domain.account.Account;
import com.example.banking.main.domain.account.AccountDoesNotExistException;
import com.example.banking.main.domain.account.AccountWithoutEnoughBalanceException;
import com.example.banking.main.domain.transaction.Transaction;
import com.example.banking.main.domain.transaction.TransactionAmount;
import com.example.banking.shared.domain.Identifier;
import lombok.Synchronized;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MakeInternationalTransactionUseCase {
    private final TransactionStorage transactionStorage;
    private final AccountStorage accountStorage;

    public MakeInternationalTransactionUseCase(TransactionStorage transactionStorage, AccountStorage accountStorage) {
        this.transactionStorage = transactionStorage;
        this.accountStorage = accountStorage;
    }

    @Synchronized
    public void invoke(Identifier from, Identifier to, TransactionAmount amount) throws AccountDoesNotExistException, AccountWithoutEnoughBalanceException {
        Optional<Account> optionalAccount = accountStorage.getById(from);
        if (optionalAccount.isEmpty()) {
            throw new AccountDoesNotExistException(from);
        }

        List<Transaction> transactions = transactionStorage.getByAccountId(from);
        Integer balance = CalculateBalanceService.calculateBalance(transactions, from);

        if (balance < amount.getQuantity()) {
            throw new AccountWithoutEnoughBalanceException(from);
        }

        Transaction transaction = Transaction.International(from, to, amount);

        transactionStorage.save(transaction);
    }
}
