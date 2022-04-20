package com.example.banking.main.infrastructure.adapter.out.in_memory;

import com.example.banking.main.application.port.out.AccountStorage;
import com.example.banking.main.domain.account.Account;
import com.example.banking.main.domain.account.AccountMother;
import com.example.banking.shared.domain.Identifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryAccountStorageTest {

    private AccountStorage accountStorage;

    @BeforeEach
    void setup() {
        accountStorage = new InMemoryAccountStorage();
    }

    @Test
    public void shouldSaveAccount() {
        Account account = AccountMother.random();

        accountStorage.save(account);
    }

    @Test
    public void shouldGetOptionalEmptyIfAccountDoesNotExists() {
        Optional<Account> optionalAccount = accountStorage.getById(Identifier.Create());

        assertTrue(optionalAccount.isEmpty());
    }

    @Test
    public void shouldGetAccountSaved() {
        Account account = AccountMother.random();

        accountStorage.save(account);
        Optional<Account> optionalAccount = accountStorage.getById(account.getIdentifier());

        assertTrue(optionalAccount.isPresent());
        Account result = optionalAccount.get();
        assertEquals(result, account);
    }

    @Test
    public void shouldDeleteAccount() {
        Account account = AccountMother.random();

        accountStorage.save(account);
        accountStorage.delete(account.getIdentifier());
        Optional<Account> optionalAccount = accountStorage.getById(account.getIdentifier());

        assertTrue(optionalAccount.isEmpty());
    }

    @Test
    public void shouldGetAccounts() {
        Account account = AccountMother.random();

        accountStorage.save(account);
        List<Account> list = accountStorage.getList();

        assertEquals(list.size(), 1);
        Account result = list.stream().findFirst().get();
        assertEquals(result, account);
    }
}
