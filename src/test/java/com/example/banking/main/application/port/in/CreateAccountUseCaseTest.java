package com.example.banking.main.application.port.in;

import com.example.banking.main.application.port.out.AccountStorage;
import com.example.banking.main.domain.account.Account;
import com.example.banking.main.domain.account.AccountName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

class CreateAccountUseCaseTest {
    @Mock
    private AccountStorage accountStorage;

    private CreateAccountUseCase createAccountUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createAccountUseCase = new CreateAccountUseCase(accountStorage);
    }

    @Test
    void shouldCreateAccountAndSave() {
        AccountName accountName = AccountName.Create("Account Test");
        doNothing().when(accountStorage).save(any(Account.class));

        createAccountUseCase.invoke(accountName);

        verify(accountStorage).save(argThat(account -> account.getName().equals(accountName)));
    }
}
