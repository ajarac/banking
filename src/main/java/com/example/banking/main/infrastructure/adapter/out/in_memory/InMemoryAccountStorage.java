package com.example.banking.main.infrastructure.adapter.out.in_memory;

import com.example.banking.main.application.port.out.AccountStorage;
import com.example.banking.main.domain.account.Account;
import com.example.banking.main.domain.account.AccountDoesNotExistException;
import com.example.banking.shared.domain.Identifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryAccountStorage implements AccountStorage {
    private final HashMap<String, Account> map = new HashMap<>();

    @Override
    public List<Account> getList() {
        return new ArrayList<Account>(map.values());
    }

    @Override
    public Optional<Account> getById(Identifier accountId) {
        String id = accountId.toString();
        return map.containsKey(id) ? Optional.of(map.get(id)) : Optional.empty();
    }

    @Override
    public void create(Account account) {
        map.put(account.getIdentifier().toString(), account);
    }

    @Override
    public void delete(Identifier accountId) {
        String id = accountId.toString();
        map.remove(id);
    }

    @Override
    public void update(Account account) throws AccountDoesNotExistException {
        String id = account.getIdentifier().toString();
        if (!map.containsKey(id)) {
            throw new AccountDoesNotExistException(account.getIdentifier());
        }
        map.replace(account.getIdentifier().toString(), account);
    }
}
