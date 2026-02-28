package com.exxecute.taskflow.controller;

import com.exxecute.taskflow.model.dto.TaskDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Task Controller Test Class.
 * Needs for testing all controller operations.
 *
 * @author Uladzislau Mikhayevich
 */
@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {
    /**
     * Controller URL.
     */
    private final static String CONTROLLER_URL = "/tasks";

    /**
     * Web Layer for test.
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Mapper.
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Tests setting task to application via controller.
     * @throws Exception Test exceptions.
     */
    @Test
    void fullFlowTest() throws Exception {
        /* Test name like class */
        String integrationalTaskName = this.getClass().getName();

        /* CONTROLLER_URL/{expected task id} */
        StringBuilder sb = new StringBuilder(CONTROLLER_URL)
                .append("/")
                .append(1);
        String integrationalTaskExpectedUrl = sb.toString();

        TaskDto dto = new TaskDto();
        dto.setTitle(integrationalTaskName);

        /* Create task */
        String response = mockMvc.perform(post(CONTROLLER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        /* Get task */
        mockMvc.perform(get(integrationalTaskExpectedUrl))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(integrationalTaskName));

        /* Get task */
        mockMvc.perform(get(CONTROLLER_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(integrationalTaskName));

        /* Delete task */
        mockMvc.perform(delete(integrationalTaskExpectedUrl))
                .andExpect(status().isNoContent());
    }
}
