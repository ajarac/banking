package com.example.banking.main.application.port.in;

import com.example.banking.main.application.port.out.AccountStorage;
import com.example.banking.main.application.port.out.TransactionStorage;
import com.example.banking.main.domain.account.Account;
import com.example.banking.main.domain.account.AccountDoesNotExistException;
import com.example.banking.main.domain.account.AccountMother;
import com.example.banking.main.domain.account.AccountWithBalanceCanNotToBeDeletedException;
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
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class DeleteAccountUseCaseTest {
    @Mock
    private AccountStorage accountStorage;
    @Mock
    private TransactionStorage transactionStorage;


    private DeleteAccountUseCase deleteAccountUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        deleteAccountUseCase = new DeleteAccountUseCase(transactionStorage, accountStorage);
    }

    @Test
    void shouldDeleteAccountByIdIfDoesNotHaveBalance() throws AccountDoesNotExistException, AccountWithBalanceCanNotToBeDeletedException {
        Account account = AccountMother.random();
        Identifier accountId = account.getIdentifier();
        when(accountStorage.getById(eq(accountId))).thenReturn(Optional.of(account));
        when(transactionStorage.getByAccountId(accountId)).thenReturn(emptyTransactions());
        doNothing().when(accountStorage).delete(eq(accountId));

        deleteAccountUseCase.invoke(accountId);

        verify(accountStorage).delete(argThat(identifier -> identifier.equals(accountId)));
    }

    @Test()
    void shouldThrowErrorWhenWeWantToDeleteANonExistsAccount() {
        Identifier accountId = Identifier.Create();
        when(accountStorage.getById(eq(accountId))).thenReturn(Optional.empty());

        doNothing().when(accountStorage).delete(eq(accountId));

        Throwable throwable = assertThrows(AccountDoesNotExistException.class, () -> deleteAccountUseCase.invoke(accountId));

        assertEquals(throwable.getMessage(), "Account with id: " + accountId.toString() + " does not exist");
    }

    @Test
    void shouldThrowErrorWhenWeWantToDeleteAAccountWithoutBalance() {
        Account account = AccountMother.random();
        Identifier accountId = account.getIdentifier();
        when(accountStorage.getById(eq(accountId))).thenReturn(Optional.of(account));
        when(transactionStorage.getByAccountId(accountId)).thenReturn(depositTransaction(accountId));
        doNothing().when(accountStorage).delete(eq(accountId));

        Throwable throwable = assertThrows(AccountWithBalanceCanNotToBeDeletedException.class, () -> deleteAccountUseCase.invoke(accountId));

        assertEquals(throwable.getMessage(), "Account with id " + accountId.toString() + " needs to do not have balance to be deleted");
    }

    private List<Transaction> emptyTransactions() {
        return List.of();
    }

    private List<Transaction> depositTransaction(Identifier accountId) {
        return List.of(TransactionMother.deposit(accountId));
    }

}
