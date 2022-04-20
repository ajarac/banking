package com.example.banking.main.infrastructure.adapter.in.controllers;

import com.example.banking.main.domain.transaction.TransactionType;
import com.example.banking.main.infrastructure.adapter.in.controllers.models.CreateTransactionRequest;
import com.example.banking.shared.domain.Identifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CreateTransactionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldBadRequestWithInvalidTransaction() throws Exception {
        CreateTransactionRequest createTransactionRequest = new CreateTransactionRequest();
        createTransactionRequest.from = "badUUID";
        createTransactionRequest.to = "badUUID";
        createTransactionRequest.amount = 0;
        createTransactionRequest.type = "Not a type";
        RequestBuilder requestBuilder = post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createTransactionRequest));

        mockMvc.perform(requestBuilder).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotFoundAccountWhenCreateTransactionWithoutAccountCreated() throws Exception {
        CreateTransactionRequest createTransactionRequest = new CreateTransactionRequest();
        createTransactionRequest.from = Identifier.Create().toString();
        createTransactionRequest.to = Identifier.Create().toString();
        createTransactionRequest.amount = 50;
        createTransactionRequest.type = TransactionType.DEPOSIT.name();
        RequestBuilder requestBuilder = post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createTransactionRequest));

        mockMvc.perform(requestBuilder).andExpect(status().isNotFound());
    }

    @Test
    public void shouldCreateTransactionAndReturnAccepted() throws Exception {
        String to = AccountUtils.createAccountAndGetAccountId(objectMapper, mockMvc);
        CreateTransactionRequest createTransactionRequest = new CreateTransactionRequest();
        createTransactionRequest.to = to;
        createTransactionRequest.amount = 50;
        createTransactionRequest.type = TransactionType.DEPOSIT.name();
        RequestBuilder requestBuilder = post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createTransactionRequest));

        mockMvc.perform(requestBuilder).andExpect(status().isAccepted());
    }
}
