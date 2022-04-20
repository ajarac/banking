package com.example.banking.main.infrastructure.adapter.in.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class GetAccountsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    void setup() throws Exception {
        AccountUtils.deleteAll(objectMapper, mockMvc);
    }

    @Test
    public void shouldGetEmptyAccountsList() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/accounts").contentType(MediaType.APPLICATION_JSON)).andReturn();
        JSONArray accountsJSON = new JSONArray(mvcResult.getResponse().getContentAsString());
        assertEquals(0, accountsJSON.length());
    }

    @Test
    public void shouldGetAccountList() throws Exception {
        AccountUtils.createAccount(objectMapper, mockMvc);
        MvcResult mvcResult = mockMvc.perform(get("/accounts").contentType(MediaType.APPLICATION_JSON)).andReturn();
        JSONArray accountsJSON = new JSONArray(mvcResult.getResponse().getContentAsString());
        assertEquals(1, accountsJSON.length());
    }
}
