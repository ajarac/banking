package com.example.banking.main.application.port.out;

import com.example.banking.main.domain.account.Account;
import com.example.banking.shared.domain.Identifier;

import java.util.List;
import java.util.Optional;

public interface AccountStorage {

    List<Account> getList();

    Optional<Account> getById(Identifier accountId);

    void save(Account account);

    void delete(Identifier accountId);
}
