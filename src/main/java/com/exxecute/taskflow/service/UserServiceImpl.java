package com.exxecute.taskflow.service;

import com.exxecute.taskflow.controller.TaskController;
import com.exxecute.taskflow.exception.found.UserNotFoundException;
import com.exxecute.taskflow.model.dto.UserDto;
import com.exxecute.taskflow.model.entity.User;
import com.exxecute.taskflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.exxecute.taskflow.logging.AppLogger;
import com.exxecute.taskflow.logging.LoggerFactory;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private static final AppLogger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User create(final UserDto userDto) {
        Objects.requireNonNull(userDto, "User must not be null");
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        log.info("Creating user with username: " + userDto.username());
        return userRepository.save(user);
    }

    @Override
    public User update(final Long id, final UserDto userDto) {
        Objects.requireNonNull(userDto, "User must not be null");
        Objects.requireNonNull(id, "id must not be null");
        User existingUser = getById(id);
        BeanUtils.copyProperties(userDto, existingUser);
        log.info("User updated" + id);
        return userRepository.save(existingUser);
    }

    @Override
    public User getById(final Long id)  {
        Objects.requireNonNull(id, "id must not be null");
        log.info("Fetching user by id: " + id);
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User getByEmail(final String email) {
        Objects.requireNonNull(email, "email must not be null");
        log.info("Method getByEmail is processing" + email);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("email" , email));
    }

    @Override
    public User getByUsername(final String username) {
        Objects.requireNonNull(username, "username must not be null");
        log.info("Method getByUsername is processing" + username);
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("username", username));
    }

    @Override
    public void delete(final Long id) {
        Objects.requireNonNull(id, "id must not be null");
        log.info("Method delete is processing" + id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userRepository.deleteById(id);
    }
}
