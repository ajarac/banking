package com.example.banking.main.infrastructure.adapter.in.controllers.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateAccountRequest {
    @NotNull
    @Size(min = 3, message = "Account name should be at least 3 characters")
    public String name;
}
