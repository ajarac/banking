package com.example.banking.main.application.port.in;

import com.example.banking.main.application.port.out.AccountStorage;
import com.example.banking.main.application.port.out.TransactionStorage;
import com.example.banking.main.domain.CalculateBalanceService;
import com.example.banking.main.domain.account.Account;
import com.example.banking.main.domain.account.AccountDoesNotExistException;
import com.example.banking.main.domain.account.AccountWithBalanceCanNotToBeDeletedException;
import com.example.banking.main.domain.transaction.Transaction;
import com.example.banking.shared.domain.Identifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeleteAccountUseCase {
    private final AccountStorage accountStorage;
    private final TransactionStorage transactionStorage;

    public DeleteAccountUseCase(TransactionStorage transactionStorage, AccountStorage accountStorage) {
        this.accountStorage = accountStorage;
        this.transactionStorage = transactionStorage;
    }

    public void invoke(Identifier accountId) throws AccountDoesNotExistException, AccountWithBalanceCanNotToBeDeletedException {
        Optional<Account> account = accountStorage.getById(accountId);
        if (account.isEmpty()) {
            throw new AccountDoesNotExistException(accountId);
        }

        List<Transaction> transactions = transactionStorage.getByAccountId(accountId);
        Integer balance = CalculateBalanceService.calculateBalance(transactions, accountId);

        if(balance != 0) {
            throw new AccountWithBalanceCanNotToBeDeletedException(accountId);
        }

        this.accountStorage.delete(accountId);
    }
}
