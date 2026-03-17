package com.exxecute.taskflow.service;

import com.exxecute.taskflow.model.dto.UserDto;
import com.exxecute.taskflow.model.entity.User;

public interface UserService {

    User create(UserDto userDto);

    User getById(Long id);

    User getByUsername(String username);

    User getByEmail(String email);

    User update(Long id, UserDto userDto);

    void delete(Long id);
}
