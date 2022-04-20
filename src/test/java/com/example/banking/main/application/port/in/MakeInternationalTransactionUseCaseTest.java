package com.example.banking.main.application.port.in;

import com.example.banking.main.application.port.out.AccountStorage;
import com.example.banking.main.application.port.out.TransactionStorage;
import com.example.banking.main.domain.account.Account;
import com.example.banking.main.domain.account.AccountDoesNotExistException;
import com.example.banking.main.domain.account.AccountMother;
import com.example.banking.main.domain.account.AccountWithoutEnoughBalanceException;
import com.example.banking.main.domain.transaction.Transaction;
import com.example.banking.main.domain.transaction.TransactionAmount;
import com.example.banking.main.domain.transaction.TransactionAmountMother;
import com.example.banking.main.domain.transaction.TransactionMother;
import com.example.banking.shared.domain.Identifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MakeInternationalTransactionUseCaseTest {
    @Mock
    private TransactionStorage transactionStorage;
    @Mock
    private AccountStorage accountStorage;

    private MakeInternationalTransactionUseCase makeInternationalTransactionUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        makeInternationalTransactionUseCase = new MakeInternationalTransactionUseCase(transactionStorage, accountStorage);
    }

    @Test
    public void shouldThrowExceptionIfAccountDoesNotExists() {
        Identifier from = Identifier.Create();
        Identifier to = Identifier.Create();
        TransactionAmount amount = TransactionAmountMother.random();
        when(accountStorage.getById(from)).thenReturn(Optional.empty());

        Throwable throwable = assertThrows(AccountDoesNotExistException.class, () -> makeInternationalTransactionUseCase.invoke(from, to, amount));

        assertEquals(throwable.getMessage(), "Account with id: " + from.getId() + " does not exist");
    }

    @Test
    public void shouldThrowExceptionIfAccountExistsAndDoesNotHaveEnoughBalance() {
        Account account = AccountMother.random();
        Identifier from = account.getIdentifier();
        Identifier to = Identifier.Create();
        TransactionAmount amount = TransactionAmountMother.of(100);
        when(accountStorage.getById(from)).thenReturn(Optional.of(account));
        when(transactionStorage.getByAccountId(from)).thenReturn(TransactionMother.toHaveBalanceOf(from, 50));

        Throwable throwable = assertThrows(AccountWithoutEnoughBalanceException.class, () -> makeInternationalTransactionUseCase.invoke(from, to, amount));

        assertEquals(throwable.getMessage(), "Account with id " + from.getId() + " has not enough balance");
    }

    @Test
    public void shouldSaveTransactionIfAccountExistsAndHaveBalance() throws AccountDoesNotExistException, AccountWithoutEnoughBalanceException {
        Account account = AccountMother.random();
        Identifier from = account.getIdentifier();
        Identifier to = Identifier.Create();
        TransactionAmount amount = TransactionAmountMother.of(10);
        when(accountStorage.getById(from)).thenReturn(Optional.of(account));
        when(transactionStorage.getByAccountId(from)).thenReturn(TransactionMother.toHaveBalanceOf(from, 50));
        doNothing().when(transactionStorage).save(any(Transaction.class));

        makeInternationalTransactionUseCase.invoke(from, to, amount);

        verify(accountStorage).getById(from);
        verify(transactionStorage).save(any(Transaction.class));
    }
}
