package com.exxecute.taskflow.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HealthControllerTest {
    private static final String CONTROLLER_URL = "/health";
    private static final String EXPECTED_CONTENT = "OK";
    @Autowired
    private MockMvc mockMvc;

    @Test
    void healthEndpoint_shouldReturnOk() throws Exception {
        mockMvc.perform(get(CONTROLLER_URL))
                .andExpect(status().isOk())
                .andExpect(content().string(EXPECTED_CONTENT));
    }
}
