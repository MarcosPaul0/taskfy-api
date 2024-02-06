package com.api.taskfy.modules.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RefreshAuthDto {
    @NotNull(message = "Token is required")
    @NotBlank(message = "Token is required")
    public String token;

    @NotNull(message = "Refresh token is required")
    @NotBlank(message = "Refresh token is required")
    public String refreshToken;
}
