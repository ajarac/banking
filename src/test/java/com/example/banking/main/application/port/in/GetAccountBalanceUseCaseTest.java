package com.example.banking.main.application.port.in;

import com.example.banking.main.application.dto.AccountBalanceDTO;
import com.example.banking.main.application.port.out.AccountStorage;
import com.example.banking.main.application.port.out.TransactionStorage;
import com.example.banking.main.domain.account.Account;
import com.example.banking.main.domain.account.AccountDoesNotExistException;
import com.example.banking.main.domain.account.AccountMother;
import com.example.banking.main.domain.transaction.Transaction;
import com.example.banking.main.domain.transaction.TransactionMother;
import com.example.banking.shared.domain.Identifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class GetAccountBalanceUseCaseTest {

    @Mock
    private TransactionStorage transactionStorage;
    @Mock
    private AccountStorage accountStorage;

    private GetAccountBalanceUseCase getAccountBalanceUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        getAccountBalanceUseCase = new GetAccountBalanceUseCase(transactionStorage, accountStorage);
    }

    @Test()
    void shouldThrowErrorWhenWeWantToCalculateAccountNonExistsAccount() {
        Identifier accountId = Identifier.Create();
        when(accountStorage.getById(eq(accountId))).thenReturn(Optional.empty());

        doNothing().when(accountStorage).delete(eq(accountId));

        Throwable throwable = assertThrows(AccountDoesNotExistException.class, () -> getAccountBalanceUseCase.invoke(accountId));

        assertEquals(throwable.getMessage(), "Account with id: " + accountId.toString() + " does not exist");
    }

    @Test
    void shouldCalculateBalance() throws AccountDoesNotExistException {
        Account account = AccountMother.random();
        Identifier accountId = account.getIdentifier();
        Transaction deposit = TransactionMother.deposit(accountId);
        when(accountStorage.getById(accountId)).thenReturn(Optional.of(account));
        when(transactionStorage.getByAccountId(accountId)).thenReturn(List.of(deposit));

        AccountBalanceDTO response = getAccountBalanceUseCase.invoke(accountId);

        assertEquals(response.balance, deposit.getTransactionAmount().getQuantity());
    }


}
