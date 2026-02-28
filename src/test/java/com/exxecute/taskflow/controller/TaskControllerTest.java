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

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {
    private final static String CONTROLLER_URL = "/tasks";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void fullFlowTest() throws Exception {
        String integrationalTaskName = this.getClass().getName();

        StringBuilder sb = new StringBuilder(CONTROLLER_URL)
                .append("/")
                .append(1);
        String integrationalTaskExpectedUrl = sb.toString();

        TaskDto dto = new TaskDto();
        dto.setTitle(integrationalTaskName);

        String response = mockMvc.perform(post(CONTROLLER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        mockMvc.perform(get(integrationalTaskExpectedUrl))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(integrationalTaskName));

        mockMvc.perform(get(CONTROLLER_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(integrationalTaskName));

        mockMvc.perform(delete(integrationalTaskExpectedUrl))
                .andExpect(status().isNoContent());
    }
}
