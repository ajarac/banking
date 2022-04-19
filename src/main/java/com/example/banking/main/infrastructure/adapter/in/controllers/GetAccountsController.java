package com.example.banking.main.infrastructure.adapter.in.controllers;

import com.example.banking.main.application.dto.AccountDTO;
import com.example.banking.main.application.port.in.GetAccountsUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/accounts")
public class GetAccountsController {

    private final GetAccountsUseCase getAccountsUseCase;

    public GetAccountsController(GetAccountsUseCase getAccountsUseCase) {
        this.getAccountsUseCase = getAccountsUseCase;
    }

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getList() {
        List<AccountDTO> accounts = getAccountsUseCase.invoke();
        return ResponseEntity.ok(accounts);
    }
}
