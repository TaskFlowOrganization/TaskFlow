package com.exxecute.taskflow.controller;

import com.exxecute.taskflow.model.dto.UserDto;
import com.exxecute.taskflow.model.entity.User;
import com.exxecute.taskflow.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.exxecute.taskflow.logging.AppLogger;
import com.exxecute.taskflow.logging.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/users")
public class UserController {

    private static final AppLogger log = LoggerFactory.getLogger(TaskController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody UserDto userCreate) {
        log.info("User created");
        return userService.create(userCreate);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@Valid @PathVariable Long id) {
        log.info("getUserById");
        return userService.getById(id);
    }

    @GetMapping("/username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserByUsername(@Valid @PathVariable String username) {
        log.info("getUserByUsername");
        return userService.getByUsername(username);
    }

    @GetMapping("/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserByEmail(@Valid @PathVariable String email) {
        log.info("getUserByEmail");
        return userService.getByEmail(email);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable Long id,
                           @Valid @RequestBody UserDto userUpdate) {
        log.info("updateUser");
        userService.update(id, userUpdate);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        log.info("Delete user, id: " + id);
        userService.delete(id);
    }
}
