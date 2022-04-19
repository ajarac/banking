package com.example.banking.main.application.port.in;

import com.example.banking.main.application.port.out.AccountStorage;
import com.example.banking.main.domain.account.Account;
import com.example.banking.main.domain.account.AccountDoesNotExistException;
import com.example.banking.shared.domain.Identifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteAccountUseCase {
    private final AccountStorage accountStorage;

    public DeleteAccountUseCase(AccountStorage accountStorage) {
        this.accountStorage = accountStorage;
    }

    public void invoke(Identifier accountId) throws AccountDoesNotExistException {
        Optional<Account> account = accountStorage.getById(accountId);
        if (account.isEmpty()) {
            throw new AccountDoesNotExistException(accountId);
        }

        this.accountStorage.delete(accountId);
    }
}
