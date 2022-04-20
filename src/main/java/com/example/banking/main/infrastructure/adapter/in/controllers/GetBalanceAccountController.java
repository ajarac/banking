package com.example.banking.main.infrastructure.adapter.in.controllers;

import com.example.banking.main.application.dto.AccountBalanceDTO;
import com.example.banking.main.application.port.in.GetAccountBalanceUseCase;
import com.example.banking.main.domain.account.AccountDoesNotExistException;
import com.example.banking.shared.domain.Identifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/accounts")
public class GetBalanceAccountController {
    private final GetAccountBalanceUseCase getAccountBalanceUseCase;

    public GetBalanceAccountController(GetAccountBalanceUseCase getAccountBalanceUseCase) {
        this.getAccountBalanceUseCase = getAccountBalanceUseCase;
    }

    @GetMapping("{accountId}")
    public ResponseEntity<AccountBalanceDTO> getBalance(@PathVariable String accountId) throws AccountDoesNotExistException {
        Identifier identifier = new Identifier(accountId);
        AccountBalanceDTO accountBalanceDTO = getAccountBalanceUseCase.invoke(identifier);
        return ResponseEntity.ok(accountBalanceDTO);
    }

}
