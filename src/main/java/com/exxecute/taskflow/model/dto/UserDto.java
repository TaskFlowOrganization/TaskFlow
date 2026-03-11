package com.exxecute.taskflow.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    @NotBlank(message = "Username can't be empty")
    @Size(min = 3, max = 30, message = "Username length must be from 3 to 30 ")
    private String username;

    @Email(message = " Invalid email format")
    @NotBlank(message = " Email can't be empty")
    private String email;
}
