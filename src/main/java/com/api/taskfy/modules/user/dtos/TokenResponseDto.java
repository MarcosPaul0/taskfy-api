package com.api.taskfy.modules.user.dtos;

import com.api.taskfy.modules.user.entities.RefreshToken;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TokenResponseDto {
    public String token;
    public RefreshToken refreshToken;
}
