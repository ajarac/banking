package com.example.banking.main.application.service;

import com.example.banking.main.application.port.in.MakeDepositTransactionUseCase;
import com.example.banking.main.application.port.in.MakeInternationalTransactionUseCase;
import com.example.banking.main.application.port.in.MakeLocalTransactionUseCase;
import com.example.banking.main.application.port.in.MakeWithdrawalTransactionUseCase;
import com.example.banking.main.domain.account.AccountDoesNotExistException;
import com.example.banking.main.domain.transaction.TransactionAmount;
import com.example.banking.main.domain.transaction.TransactionType;
import com.example.banking.shared.domain.Identifier;
import org.springframework.stereotype.Service;

@Service
public class CreateTransactionService {

    private final MakeDepositTransactionUseCase makeDepositTransactionUseCase;
    private final MakeInternationalTransactionUseCase makeInternationalTransactionUseCase;
    private final MakeLocalTransactionUseCase makeLocalTransactionUseCase;
    private final MakeWithdrawalTransactionUseCase makeWithdrawalTransactionUseCase;

    public CreateTransactionService(MakeDepositTransactionUseCase makeDepositTransactionUseCase, MakeInternationalTransactionUseCase makeInternationalTransactionUseCase, MakeLocalTransactionUseCase makeLocalTransactionUseCase, MakeWithdrawalTransactionUseCase makeWithdrawalTransactionUseCase) {
        this.makeDepositTransactionUseCase = makeDepositTransactionUseCase;
        this.makeInternationalTransactionUseCase = makeInternationalTransactionUseCase;
        this.makeLocalTransactionUseCase = makeLocalTransactionUseCase;
        this.makeWithdrawalTransactionUseCase = makeWithdrawalTransactionUseCase;
    }

    public void facade(Identifier from, Identifier to, TransactionAmount amount, TransactionType type) throws AccountDoesNotExistException {
        switch (type) {
            case WITHDRAWAL:
                makeWithdrawalTransactionUseCase.invoke(from, amount);
                break;
            case DEPOSIT:
                makeDepositTransactionUseCase.invoke(to, amount);
                break;
            case LOCAL:
                makeLocalTransactionUseCase.invoke(from, to, amount);
                break;
            case INTERNATIONAL:
                makeInternationalTransactionUseCase.invoke(from, to, amount);
                break;
        }
    }

}
