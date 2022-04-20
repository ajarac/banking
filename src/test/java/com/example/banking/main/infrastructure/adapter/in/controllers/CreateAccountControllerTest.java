package com.example.banking.main.infrastructure.adapter.in.controllers;

import com.example.banking.main.infrastructure.adapter.in.controllers.models.CreateAccountRequest;
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
class CreateAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void shouldBadRequestWithBadAccountName() throws Exception {
        CreateAccountRequest createAccountRequest = new CreateAccountRequest();
        createAccountRequest.name = "";
        RequestBuilder requestBuilder = post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createAccountRequest));
        mockMvc.perform(requestBuilder).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldCreateAccountAndReturnAccepted() throws Exception {
        CreateAccountRequest createAccountRequest = new CreateAccountRequest();
        createAccountRequest.name = "Account Test";
        RequestBuilder requestBuilder = post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createAccountRequest));
        mockMvc.perform(requestBuilder).andExpect(status().isAccepted());
    }
}
