package com.exxecute.taskflow.controller;

import com.exxecute.taskflow.model.dto.TaskDto;
import com.exxecute.taskflow.model.enums.Status;
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
@AutoConfigureMockMvc(addFilters = false)
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

        TaskDto dto = new TaskDto();
        dto.setTitle(integrationalTaskName);
        dto.setStatus(Status.NEW);

        /* Create task */
        String response = mockMvc.perform(post(CONTROLLER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Long ceratedId = objectMapper.readTree(response).get("id").asLong();
        String taskUrl = CONTROLLER_URL + "/" + ceratedId;

        /* Get task */
        mockMvc.perform(get(taskUrl))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(integrationalTaskName));

        /* Get task */
        mockMvc.perform(get(CONTROLLER_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(integrationalTaskName));

        /* Delete task */
        mockMvc.perform(delete(taskUrl))
                .andExpect(status().isNoContent());
    }

    /**
     * Should return 400 if title is null.
     */
    @Test
    void createTask_shouldFail_whenTitleIsNull() throws Exception {

        TaskDto dto = new TaskDto();
        dto.setTitle(null);
        dto.setStatus(Status.NEW);

        mockMvc.perform(post(CONTROLLER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    /**
     * Should return 400 if status is null.
     */
    @Test
    void createTask_shouldFail_whenStatusIsNull() throws Exception {

        TaskDto dto = new TaskDto();
        dto.setTitle(this.getClass().getName());
        dto.setStatus(null);

        mockMvc.perform(post(CONTROLLER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    /**
     * Should return 400 if title is empty.
     */
    @Test
    void createTask_shouldFail_whenTitleIsEmpty() throws Exception {

        TaskDto dto = new TaskDto();
        dto.setTitle("");
        dto.setStatus(Status.NEW);

        mockMvc.perform(post(CONTROLLER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    /**
     * Should return 400 if description is too long.
     */
    @Test
    void createTask_shouldFail_whenDescriptionTooLong() throws Exception {

        TaskDto dto = new TaskDto();
        dto.setTitle(this.getClass().getName());
        dto.setStatus(Status.NEW);
        dto.setDescription("a".repeat(5001));

        mockMvc.perform(post(CONTROLLER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    /**
     * Should create task when DTO is valid.
     */
    @Test
    void createTask_shouldSucceed_whenDtoIsValid() throws Exception {

        TaskDto dto = new TaskDto();
        dto.setTitle(this.getClass().getName());
        dto.setStatus(Status.NEW);
        dto.setDescription("Some description");

        mockMvc.perform(post(CONTROLLER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(this.getClass().getName()));
    }

    /* TODO: SCRUM-37 assigning task to user (Need to add users). */
}
