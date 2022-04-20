package com.example.banking.main.infrastructure.adapter.in.controllers;

import com.example.banking.main.infrastructure.adapter.in.controllers.models.CreateAccountRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AccountUtils {

    public static String createAccountAndGetAccountId(ObjectMapper objectMapper, MockMvc mockMvc) throws Exception {
        createAccount(objectMapper, mockMvc);

        JSONArray accountsJSON = getAccounts(mockMvc);
        JSONObject account = accountsJSON.getJSONObject(0);
        return account.getString("id");
    }

    private static JSONArray getAccounts(MockMvc mockMvc) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/accounts").contentType(MediaType.APPLICATION_JSON)).andReturn();
        return new JSONArray(mvcResult.getResponse().getContentAsString());
    }

    public static void createAccount(ObjectMapper objectMapper, MockMvc mockMvc) throws Exception {
        CreateAccountRequest createAccountRequest = new CreateAccountRequest();
        createAccountRequest.name = "Account Test";
        RequestBuilder createAccountRequestBuilder = post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createAccountRequest));
        mockMvc.perform(createAccountRequestBuilder).andExpect(status().isAccepted());
    }

    public static void deleteAll(ObjectMapper objectMapper, MockMvc mockMvc) throws Exception {
        JSONArray accounts = getAccounts(mockMvc);
        for (var i = 0; i < accounts.length(); i++) {
            RequestBuilder requestBuilder = delete("/accounts/" + accounts.getJSONObject(i).getString("id"));
            mockMvc.perform(requestBuilder).andExpect(status().isAccepted());
        }
    }
}
