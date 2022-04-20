package com.example.banking.main.infrastructure.adapter.in.controllers;

import com.example.banking.shared.domain.Identifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DeleteAccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldNotFoundAccountWhenDeleteAccountNonExist() throws Exception {
        String accountId = Identifier.Create().toString();
        RequestBuilder requestBuilder = delete("/accounts/" + accountId);
        mockMvc.perform(requestBuilder).andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteAccount() throws Exception {
        String accountId = AccountUtils.createAccountAndGetAccountId(objectMapper, mockMvc);
        RequestBuilder requestBuilder = delete("/accounts/" + accountId);
        mockMvc.perform(requestBuilder).andExpect(status().isAccepted());
    }
}
