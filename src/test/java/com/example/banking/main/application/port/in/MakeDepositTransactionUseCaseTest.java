package com.example.banking.main.application.port.in;

import com.example.banking.main.application.port.out.AccountStorage;
import com.example.banking.main.application.port.out.TransactionStorage;
import com.example.banking.main.domain.account.Account;
import com.example.banking.main.domain.account.AccountDoesNotExistException;
import com.example.banking.main.domain.account.AccountMother;
import com.example.banking.main.domain.transaction.Transaction;
import com.example.banking.main.domain.transaction.TransactionAmount;
import com.example.banking.main.domain.transaction.TransactionAmountMother;
import com.example.banking.shared.domain.Identifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MakeDepositTransactionUseCaseTest {
    @Mock
    private TransactionStorage transactionStorage;
    @Mock
    private AccountStorage accountStorage;

    private MakeDepositTransactionUseCase makeDepositTransactionUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        makeDepositTransactionUseCase = new MakeDepositTransactionUseCase(transactionStorage, accountStorage);
    }

    @Test
    public void shouldThrowExceptionIfAccountDoesNotExists() {
        Identifier accountId = Identifier.Create();
        TransactionAmount amount = TransactionAmountMother.random();
        when(accountStorage.getById(accountId)).thenReturn(Optional.empty());

        Throwable throwable = assertThrows(AccountDoesNotExistException.class, () -> makeDepositTransactionUseCase.invoke(accountId, amount));

        assertEquals(throwable.getMessage(), "Account with id: " + accountId.toString() + " does not exist");
    }

    @Test
    public void shouldSaveTransactionIfAccountExists() throws AccountDoesNotExistException {
        Account account = AccountMother.random();
        Identifier accountId = account.getIdentifier();
        TransactionAmount amount = TransactionAmountMother.random();
        when(accountStorage.getById(accountId)).thenReturn(Optional.of(account));
        doNothing().when(transactionStorage).save(any(Transaction.class));

        makeDepositTransactionUseCase.invoke(accountId, amount);

        verify(accountStorage).getById(accountId);
        verify(transactionStorage).save(any(Transaction.class));
    }
}
