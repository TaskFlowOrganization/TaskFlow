package com.exxecute.taskflow.controller;

import com.exxecute.taskflow.model.dto.UserDto;
import com.exxecute.taskflow.model.entity.User;
import com.exxecute.taskflow.repository.UserRepository;
import com.exxecute.taskflow.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
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

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    void createUserTest() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUsername("test");
        userDto.setEmail("test@gmail.com");

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    void createUser_shouldFail_whenUsernameIsEmpty() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUsername("");
        userDto.setEmail("test@gmail.com");

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void createUser_shouldFail_whenEmailIsEmpty() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUsername("test");
        userDto.setEmail("");

        mockMvc.perform(post("/users")
        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void createUser_shouldFail_whenEmailIsIncorrect() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUsername("test");
        userDto.setEmail("abobas");

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateUserTest() throws Exception {

        UserDto createDto = new UserDto();
        createDto.setUsername("oldTest");
        createDto.setEmail("oldTest@gmail.com");


        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDto)))
                .andDo(print())
                .andExpect(status().isCreated());

        Long id = userRepository.findAll().stream().findFirst().get().getId();

        UserDto updateDto = new UserDto();
        updateDto.setUsername("newTest");
        updateDto.setEmail("newTest@gmail.com");

        mockMvc.perform(put("/users/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDto)))
                .andDo(print())
                .andExpect(status().isNoContent());

        entityManager.flush();
        entityManager.clear();

        User updatedUser = userRepository.findById(id).get();

        assertEquals("newTest", updatedUser.getUsername());
        assertEquals("newTest@gmail.com", updatedUser.getEmail());
    }

    @Test
    void updateUser_shouldFail_whenUsernameIsEmpty() throws Exception {
        UserDto createDto = new UserDto();
        createDto.setUsername("oldTest");
        createDto.setEmail("oldTest@gmail.com");

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDto)))
                .andDo(print())
                .andExpect(status().isCreated());

        Long id = userRepository.findAll().stream().findFirst().get().getId();

        UserDto updateDto = new UserDto();
        updateDto.setUsername("");
        updateDto.setEmail("newTest@gmail.com");

        mockMvc.perform(put("/users/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());

        entityManager.flush();
        entityManager.clear();

        User updatedUser = userRepository.findById(id).get();

        assertEquals("oldTest", updatedUser.getUsername());
        assertEquals("oldTest@gmail.com", updatedUser.getEmail());
    }

    @Test
    void updateUser_shouldFail_whenEmailIsEmpty() throws Exception {

        UserDto createDto = new UserDto();

        createDto.setUsername("oldTest");
        createDto.setEmail("oldTest@gmail.com");

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDto)))
                .andDo(print())
                .andExpect(status().isCreated());


        Long id = userRepository.findAll().stream().findFirst().get().getId();

        UserDto updateDto = new UserDto();
        updateDto.setUsername("newTest");
        updateDto.setEmail("");

        mockMvc.perform(put("/users/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());

        entityManager.flush();
        entityManager.clear();

        User updatedUser = userRepository.findById(id).get();

        assertEquals("oldTest", updatedUser.getUsername());
        assertEquals("oldTest@gmail.com", updatedUser.getEmail());
    }

    @Test
    void updateUser_shouldFail_whenEmailIsInvalid() throws Exception {

        UserDto createDto = new UserDto();

        createDto.setUsername("oldTest");
        createDto.setEmail("oldTest@gmail.com");

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDto)))
                .andDo(print())
                .andExpect(status().isCreated());


        Long id = userRepository.findAll().stream().findFirst().get().getId();

        UserDto updateDto = new UserDto();
        updateDto.setUsername("newTest");
        updateDto.setEmail("abobka123");

        mockMvc.perform(put("/users/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());

        entityManager.flush();
        entityManager.clear();

        User updatedUser = userRepository.findById(id).get();

        assertEquals("oldTest", updatedUser.getUsername());
        assertEquals("oldTest@gmail.com", updatedUser.getEmail());
    }

    @Test
    void getUserById() throws Exception {
         UserDto userDto = new UserDto();
         userDto.setUsername("newTest");
         userDto.setEmail("newTest@gmail.com");

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andDo(print())
                .andExpect(status().isCreated());

        Long id = userRepository.findAll().stream().findFirst().get().getId();

          mockMvc.perform(get("/users/{id}", id))
                  .andDo(print())
                  .andExpect(status().isOk())
                  .andExpect(jsonPath("$.username").value("newTest"))
                  .andExpect(jsonPath("$.email").value("newTest@gmail.com"));

    }

    @Test
    void getUserById_shouldFail_whenIdIsIncorrect() throws Exception {

        mockMvc.perform(get("/users/{id}", 999))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getUserByUsername() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUsername("newTest");
        userDto.setEmail("newTest@gmail.com");

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andDo(print())
                .andExpect(status().isCreated());

        mockMvc.perform(get("/users/username/{username}", userDto.getUsername()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("newTest"))
                .andExpect(jsonPath("$.email").value("newTest@gmail.com"));
    }

    @Test
    void getUserByUsername_shouldFail_whenUsernameIsIncorrect() throws Exception {

        mockMvc.perform(get("/users/username/{username}", "alesha"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getUserByEmail() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUsername("newTest");
        userDto.setEmail("newTest@gmail.com");

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andDo(print())
                .andExpect(status().isCreated());

        mockMvc.perform(get("/users/email/{email}", userDto.getEmail()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("newTest"))
                .andExpect(jsonPath("$.email").value("newTest@gmail.com"));
    }

    @Test
    void getUserByEmail_shouldFail_whenEmailIsIncorrect() throws Exception {
        mockMvc.perform(get("/users/email/{email}", "alesha"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUsername("newTest");
        userDto.setEmail("newTest@gmail.com");

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andDo(print())
                .andExpect(status().isCreated());

        Long id = userRepository.findAll().stream().findFirst().get().getId();

        mockMvc.perform(delete("/users/{id}", id))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertFalse(userRepository.findById(id).isPresent());
    }

    @Test
    void deleteUser_shouldReturn404_whenUserNotFound() throws Exception {
        mockMvc.perform(delete("/users/{id}", 999))
                .andExpect(status().isNotFound());
    }
}
