package com.example.banking.main.infrastructure.adapter.in.controllers;

import com.example.banking.shared.domain.Identifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GetBalanceAccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldNotFoundIfAccountDoesNotExist() throws Exception {
        String accountId = Identifier.Create().getId();
        RequestBuilder requestBuilder = get("/accounts/" + accountId);
        mockMvc.perform(requestBuilder).andExpect(status().isNotFound());
    }

    @Test
    public void shouldGetBalanceOfAccount() throws Exception {
        String accountId = AccountUtils.createAccountAndGetAccountId(objectMapper, mockMvc);
        RequestBuilder requestBuilder = get("/accounts/" + accountId);
        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();

        JSONObject accountBalance = new JSONObject(result.getResponse().getContentAsString());
        assertEquals(accountBalance.getString("id"), accountId);
        assertEquals(accountBalance.getInt("balance"), 0);
    }
}
