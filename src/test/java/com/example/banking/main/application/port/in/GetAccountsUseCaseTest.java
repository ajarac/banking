package com.example.banking.main.application.port.in;

import com.example.banking.main.application.dto.AccountDTO;
import com.example.banking.main.application.port.out.AccountStorage;
import com.example.banking.main.domain.account.Account;
import com.example.banking.main.domain.account.AccountMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GetAccountsUseCaseTest {
    @Mock
    private AccountStorage accountStorage;

    private GetAccountsUseCase getAccountsUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        getAccountsUseCase = new GetAccountsUseCase(accountStorage);
    }

    @Test
    public void shouldReturnListOfExistingAccounts() {
        Account account = AccountMother.random();
        List<Account> expected = List.of(account);
        when(accountStorage.getList()).thenReturn(expected);

        List<AccountDTO> accounts = getAccountsUseCase.invoke();
        AccountDTO accountDTO = accounts.stream().findFirst().get();
        // assertArrayEquals(expected, accounts);
        assertEquals(expected.size(), accounts.size());
        assertEquals(accountDTO.id, account.getIdentifier().toString());
        assertEquals(accountDTO.name, account.getName().toString());
    }

}
