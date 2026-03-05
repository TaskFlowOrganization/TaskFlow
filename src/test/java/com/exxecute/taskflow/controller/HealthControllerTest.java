package com.exxecute.taskflow.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Task Controller Test Class for health controller.
 *
 * @author Uladzislau Mikhayevich
 */
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class HealthControllerTest {
    /**
     * Controller url.
     */
    private static final String CONTROLLER_URL = "/health";
    /**
     * Answer content expecting OK.
     */
    private static final String EXPECTED_CONTENT = "OK";
    /**
     * Web Layer for test.
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Test for returning ok from application.
     * @throws Exception Test exception.
     */
    @Test
    void healthEndpoint_shouldReturnOk() throws Exception {
        mockMvc.perform(get(CONTROLLER_URL))
                .andExpect(status().isOk())
                .andExpect(content().string(EXPECTED_CONTENT));
    }
}
