package com.example.banking.main.infrastructure.adapter.in.controllers;

import com.example.banking.main.application.port.in.CreateAccountUseCase;
import com.example.banking.main.domain.account.AccountName;
import com.example.banking.main.infrastructure.adapter.in.controllers.models.CreateAccountRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

@Controller
@RequestMapping("/accounts")
public class CreateAccountController {

    private final CreateAccountUseCase createAccountUseCase;

    public CreateAccountController(CreateAccountUseCase createAccountUseCase) {
        this.createAccountUseCase = createAccountUseCase;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void create(@Valid @RequestBody CreateAccountRequest createAccountRequest) {
        AccountName accountName = AccountName.Create(createAccountRequest.name);
        createAccountUseCase.invoke(accountName);
    }
}


