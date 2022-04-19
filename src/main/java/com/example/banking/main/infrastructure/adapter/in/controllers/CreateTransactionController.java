package com.example.banking.main.infrastructure.adapter.in.controllers;

import com.example.banking.main.application.service.CreateTransactionService;
import com.example.banking.main.domain.account.AccountDoesNotExistException;
import com.example.banking.main.domain.transaction.TransactionAmount;
import com.example.banking.main.domain.transaction.TransactionType;
import com.example.banking.main.infrastructure.adapter.in.controllers.models.CreateTransactionRequest;
import com.example.banking.shared.domain.Identifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

@Controller
@RequestMapping("/transactions")
public class CreateTransactionController {

    private final CreateTransactionService createTransactionService;

    public CreateTransactionController(CreateTransactionService createTransactionService) {
        this.createTransactionService = createTransactionService;
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void create(@Valid @RequestBody CreateTransactionRequest createTransactionRequest) throws AccountDoesNotExistException {
        Identifier from = new Identifier(createTransactionRequest.from);
        Identifier to = new Identifier(createTransactionRequest.to);
        TransactionAmount amount = TransactionAmount.Create(createTransactionRequest.amount);
        TransactionType type = TransactionType.valueOf(createTransactionRequest.type);

        createTransactionService.facade(from, to, amount, type);
    }
}
