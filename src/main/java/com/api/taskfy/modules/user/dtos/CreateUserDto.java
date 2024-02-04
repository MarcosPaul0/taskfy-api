package com.api.taskfy.modules.user.dtos;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.api.taskfy.constants.RegularExpressions;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CreateUserDto {
    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is required")
    @Length(min = 10, message = "Name must have at least 10 characters")
    @Length(max = 100, message = "Name must have a maximum of 100 characters")
    public String name;

    @NotNull(message = "Email is required")
    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid")
    public String email;

    @NotNull(message = "Password is required")
    @NotBlank(message = "Password is required")
    @Length(min = 10, message = "Password must have at least 10 characters")
    @Pattern(regexp = RegularExpressions.PASSWORD, message = "Password is invalid")
    public String password;
}
