package com.example.banking.main.infrastructure.adapter.in.controllers.models;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Size;

public class CreateAccountRequest {
    @Size(min = 3, message = "Account name should be at least 3 characters")
    public String name;
}
