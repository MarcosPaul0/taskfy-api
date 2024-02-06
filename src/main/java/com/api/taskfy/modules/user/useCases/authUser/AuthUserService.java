package com.api.taskfy.modules.user.useCases.authUser;

import com.api.taskfy.errors.user.InvalidCredentialsException;
import com.api.taskfy.modules.user.dtos.AuthUserDto;
import com.api.taskfy.modules.user.dtos.TokenResponseDto;
import com.api.taskfy.modules.user.entities.User;


import com.api.taskfy.modules.user.repositories.UserRepository;
import com.api.taskfy.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService {
    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${api.security.token.secret}")
    private String secret;

    public TokenResponseDto execute(AuthUserDto authUserDto) {
        var userFound = this.userRepository.findByEmail(authUserDto.email);

        if (userFound.isEmpty()) {
            throw new InvalidCredentialsException();
        }

        var userPassword = new UsernamePasswordAuthenticationToken(authUserDto.email, authUserDto.password);

        try {
            var auth = this.authenticationManager.authenticate(userPassword);

            var user = (User) auth.getPrincipal();

            var token = this.tokenService.generateToken(user, secret);

            var refreshToken = this.tokenService.generateRefreshToken(user);

            return new TokenResponseDto(token, refreshToken);
        } catch (Exception error) {
            throw new InvalidCredentialsException();
        }
    }
}
