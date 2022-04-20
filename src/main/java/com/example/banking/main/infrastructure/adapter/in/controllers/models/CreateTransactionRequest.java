package com.example.banking.main.infrastructure.adapter.in.controllers.models;

import com.example.banking.main.domain.transaction.TransactionType;
import com.example.banking.shared.infrastructure.in.controllers.UUID;
import com.example.banking.shared.infrastructure.in.controllers.ValueOfEnum;

import javax.validation.constraints.Min;

public class CreateTransactionRequest {

    @UUID
    public String from;
    @UUID
    public String to;
    @Min(1)
    public Integer amount;
    @ValueOfEnum(enumClass = TransactionType.class)
    public String type;
}
