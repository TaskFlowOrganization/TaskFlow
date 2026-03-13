package com.exxecute.taskflow.service;

import com.exxecute.taskflow.exception.found.UserNotFoundException;
import com.exxecute.taskflow.model.dto.UserDto;
import com.exxecute.taskflow.model.entity.User;
import com.exxecute.taskflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User create(final UserDto userDto) {
        Objects.requireNonNull(userDto, "User must not be null");

        User user = new User();
        BeanUtils.copyProperties(userDto, user);

        return userRepository.save(user);
    }

    @Override
    public User update(final Long id, final UserDto userDto) {
        Objects.requireNonNull(userDto, "User must not be null");
        Objects.requireNonNull(id, "id must not be null");
        User existingUser = getById(id);
        BeanUtils.copyProperties(userDto, existingUser);
        return userRepository.save(existingUser);
    }

    @Override
    public User getById(final Long id)  {
        Objects.requireNonNull(id, "id must not be null");
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User getByEmail(final String email) {
        Objects.requireNonNull(email, "email must not be null");
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("email" , email));
    }

    @Override
    public User getByUsername(final String username) {
        Objects.requireNonNull(username, "username must not be null");
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("username", username));
    }

    @Override
    public void delete(final Long id) {
        Objects.requireNonNull(id, "id must not be null");

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userRepository.deleteById(id);
    }
}
