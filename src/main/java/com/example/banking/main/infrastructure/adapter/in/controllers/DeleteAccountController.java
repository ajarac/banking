package com.example.banking.main.infrastructure.adapter.in.controllers;

import com.example.banking.main.application.port.in.DeleteAccountUseCase;
import com.example.banking.main.domain.account.AccountDoesNotExistException;
import com.example.banking.main.domain.account.AccountWithBalanceCanNotToBeDeletedException;
import com.example.banking.shared.domain.Identifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/accounts")
public class DeleteAccountController {

    private final DeleteAccountUseCase deleteAccountUseCase;

    public DeleteAccountController(DeleteAccountUseCase deleteAccountUseCase) {
        this.deleteAccountUseCase = deleteAccountUseCase;
    }

    @DeleteMapping("{accountId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable String accountId) throws AccountDoesNotExistException, AccountWithBalanceCanNotToBeDeletedException {
        Identifier identifier = new Identifier(accountId);
        deleteAccountUseCase.invoke(identifier);
    }
}
