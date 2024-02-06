package com.api.taskfy.modules.user.useCases.authRefresh;

import com.api.taskfy.errors.user.InvalidRefreshTokenException;
import com.api.taskfy.errors.user.RefreshTokenExpiredException;
import com.api.taskfy.modules.user.dtos.RefreshAuthDto;
import com.api.taskfy.modules.user.dtos.TokenResponseDto;
import com.api.taskfy.modules.user.entities.RefreshToken;
import com.api.taskfy.modules.user.repositories.RefreshTokenRepository;
import com.api.taskfy.modules.user.repositories.UserRepository;
import com.api.taskfy.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthRefreshService {
    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @Value("${api.security.token.secret}")
    private String secret;

    public TokenResponseDto execute(RefreshAuthDto refreshAuthDto) {
        var refreshTokenFound = this.refreshTokenRepository.findById(refreshAuthDto.refreshToken);

        if (refreshTokenFound.isEmpty()) {
            throw new InvalidRefreshTokenException();
        }

        RefreshToken refreshToken = refreshTokenFound.get();

        var refreshTokenIsExpired = refreshToken.getExpiresAt().isBefore(LocalDateTime.now());

        if (refreshTokenIsExpired) {
            throw new RefreshTokenExpiredException();
        }

        var userFound = this.userRepository.findById(refreshToken.getUserId());

        if (userFound.isEmpty()) {
            throw new InvalidRefreshTokenException();
        }

        var newToken = this.tokenService.generateToken(userFound.get(), secret);

        return new TokenResponseDto(newToken, null);
    }
}
