package com.example.banking.main.application.port.out;

import com.example.banking.main.domain.account.Account;
import com.example.banking.main.domain.account.AccountDoesNotExistException;
import com.example.banking.shared.domain.Identifier;

import java.util.List;
import java.util.Optional;

public interface AccountStorage {

    List<Account> getList();

    Optional<Account> getById(Identifier accountId);

    void create(Account account);

    void delete(Identifier accountId);

    void update(Account account) throws AccountDoesNotExistException;
}
