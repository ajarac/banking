package com.example.banking.main.application.port.in;

import com.example.banking.main.application.dto.AccountBalanceDTO;
import com.example.banking.main.application.port.out.AccountStorage;
import com.example.banking.main.application.port.out.TransactionStorage;
import com.example.banking.main.domain.CalculateBalanceService;
import com.example.banking.main.domain.account.Account;
import com.example.banking.main.domain.account.AccountDoesNotExistException;
import com.example.banking.main.domain.transaction.Transaction;
import com.example.banking.shared.domain.Identifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetAccountBalanceUseCase {

    private final TransactionStorage transactionStorage;
    private final AccountStorage accountStorage;

    public GetAccountBalanceUseCase(TransactionStorage transactionStorage, AccountStorage accountStorage) {
        this.transactionStorage = transactionStorage;
        this.accountStorage = accountStorage;
    }

    public AccountBalanceDTO invoke(Identifier accountId) throws AccountDoesNotExistException {
        Optional<Account> accountOptional = accountStorage.getById(accountId);
        if (accountOptional.isEmpty()) {
            throw new AccountDoesNotExistException(accountId);
        }

        List<Transaction> transactions = transactionStorage.getByAccountId(accountId);
        Integer balance = CalculateBalanceService.calculateBalance(transactions, accountId);
        return new AccountBalanceDTO(accountId.toString(), balance);
    }

}
