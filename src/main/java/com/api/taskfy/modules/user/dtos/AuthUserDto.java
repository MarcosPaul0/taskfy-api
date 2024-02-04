package com.api.taskfy.modules.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AuthUserDto {
    @NotNull(message = "Email is required")
    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid")
    public String email;

    @NotNull(message = "Password is required")
    @NotBlank(message = "Password is required")
    public String password;
}
