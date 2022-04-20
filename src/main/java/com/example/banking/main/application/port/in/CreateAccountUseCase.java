package com.example.banking.main.application.port.in;

import com.example.banking.main.application.port.out.AccountStorage;
import com.example.banking.main.domain.account.Account;
import com.example.banking.main.domain.account.AccountName;
import org.springframework.stereotype.Service;

@Service
public class CreateAccountUseCase {

    private final AccountStorage storage;

    public CreateAccountUseCase(AccountStorage storage) {
        this.storage = storage;
    }

    public void invoke(AccountName name) {
        Account account = Account.Create(name);
        storage.save(account);
    }
}
