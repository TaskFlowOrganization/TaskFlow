package com.exxecute.taskflow.controller;

import com.exxecute.taskflow.model.dto.UserDto;
import com.exxecute.taskflow.model.entity.User;
import com.exxecute.taskflow.repository.UserRepository;
import com.exxecute.taskflow.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Transactional
public class UserControllerTest {

    private final static String USER_URL = "/users";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager entityManager;

    @AfterEach
    void clearDb() {
        entityManager.flush();
        entityManager.clear();
    }

    @Test
    void createUserTest() throws Exception {
        UserDto userDto = new UserDto("test", "test@gmail.com");

        mockMvc.perform(post(USER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    void createUserShouldFailWhenUsernameIsEmpty() throws Exception {
        UserDto userDto = new UserDto("","test@gmail.com" );

        mockMvc.perform(post(USER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void createUserShouldFailWhenEmailIsEmpty() throws Exception {
        UserDto userDto = new UserDto("test", "");

        mockMvc.perform(post(USER_URL)
        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void createUserShouldFailWhenEmailIsIncorrect() throws Exception {
        UserDto userDto = new UserDto("test","abobas" );

        mockMvc.perform(post(USER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateUserTest() throws Exception {
        UserDto createDto = new UserDto("oldTest", "oldTest@gmail.com");

        mockMvc.perform(post(USER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDto)))
                .andDo(print())
                .andExpect(status().isCreated());

        Long id = userRepository.findAll().stream().findFirst().get().getId();

        UserDto updateDto = new UserDto("newTest", "newTest@gmail.com");

        mockMvc.perform(put(USER_URL + "/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDto)))
                .andDo(print())
                .andExpect(status().isNoContent());

        User updatedUser = userRepository.findById(id).get();

        assertEquals("newTest", updatedUser.getUsername());
        assertEquals("newTest@gmail.com", updatedUser.getEmail());
    }

    @Test
    void updateUserShouldFailWhenUsernameIsEmpty() throws Exception {
        UserDto createDto = new UserDto("oldTest","oldTest@gmail.com");

        mockMvc.perform(post(USER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDto)))
                .andDo(print())
                .andExpect(status().isCreated());

        Long id = userRepository.findAll().stream().findFirst().get().getId();

        UserDto updateDto = new UserDto("", "newTest@gmail.com");

        mockMvc.perform(put(USER_URL + "/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());

        User updatedUser = userRepository.findById(id).get();

        assertEquals("oldTest", updatedUser.getUsername());
        assertEquals("oldTest@gmail.com", updatedUser.getEmail());
    }

    @Test
    void updateUserShouldFailWhenEmailIsEmpty() throws Exception {
        UserDto createDto = new UserDto("oldTest", "oldTest@gmail.com");

        mockMvc.perform(post(USER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDto)))
                .andDo(print())
                .andExpect(status().isCreated());


        Long id = userRepository.findAll().stream().findFirst().get().getId();

        UserDto updateDto = new UserDto("newTest", "");

        mockMvc.perform(put(USER_URL + "/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());

        User updatedUser = userRepository.findById(id).get();

        assertEquals("oldTest", updatedUser.getUsername());
        assertEquals("oldTest@gmail.com", updatedUser.getEmail());
    }

    @Test
    void updateUserShouldFailWhenEmailIsInvalid() throws Exception {

        UserDto createDto = new UserDto("oldTest","oldTest@gmail.com");

        mockMvc.perform(post(USER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDto)))
                .andDo(print())
                .andExpect(status().isCreated());


        Long id = userRepository.findAll().stream().findFirst().get().getId();

        UserDto updateDto = new UserDto("newTest","abobka123");

        mockMvc.perform(put(USER_URL + "/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());

        User updatedUser = userRepository.findById(id).get();

        assertEquals("oldTest", updatedUser.getUsername());
        assertEquals("oldTest@gmail.com", updatedUser.getEmail());
    }

    @Test
    void getUserById() throws Exception {
         UserDto userDto = new UserDto("newTest", "newTest@gmail.com");

        mockMvc.perform(post(USER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andDo(print())
                .andExpect(status().isCreated());

        Long id = userRepository.findAll().stream().findFirst().get().getId();

          mockMvc.perform(get(USER_URL + "/{id}", id))
                  .andDo(print())
                  .andExpect(status().isOk())
                  .andExpect(jsonPath("$.username").value("newTest"))
                  .andExpect(jsonPath("$.email").value("newTest@gmail.com"));
    }

    @Test
    void getUserByIdShouldFailWhenIdIsIncorrect() throws Exception {
        mockMvc.perform(get(USER_URL + "/{id}", 999))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getUserByUsername() throws Exception {
        UserDto userDto = new UserDto("newTest", "newTest@gmail.com");

        mockMvc.perform(post(USER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andDo(print())
                .andExpect(status().isCreated());

        mockMvc.perform(get(USER_URL + "/username/{username}", userDto.username()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("newTest"))
                .andExpect(jsonPath("$.email").value("newTest@gmail.com"));
    }

    @Test
    void getUserByUsernameShouldFailWhenUsernameIsIncorrect() throws Exception {

        mockMvc.perform(get(USER_URL + "/username/{username}", "alesha"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getUserByEmail() throws Exception {
        UserDto userDto = new UserDto("newTest", "newTest@gmail.com");

        mockMvc.perform(post(USER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andDo(print())
                .andExpect(status().isCreated());

        mockMvc.perform(get(USER_URL + "/email/{email}", userDto.email()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("newTest"))
                .andExpect(jsonPath("$.email").value("newTest@gmail.com"));
    }

    @Test
    void getUserByEmailShouldFailWhenEmailIsIncorrect() throws Exception {
        mockMvc.perform(get(USER_URL + "/email/{email}", "alesha"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteUserTestShouldDelete() throws Exception {
        UserDto userDto = new UserDto("newTest", "newTest@gmail.com");

        mockMvc.perform(post(USER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andDo(print())
                .andExpect(status().isCreated());

        Long id = userRepository.findAll().stream().findFirst().get().getId();

        mockMvc.perform(delete(USER_URL + "/{id}", id))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertFalse(userRepository.findById(id).isPresent());
    }

    @Test
    void deleteUserShouldReturn404WhenUserNotFound() throws Exception {
        mockMvc.perform(delete(USER_URL + "/{id}", 999))
                .andExpect(status().isNotFound());
    }
}
