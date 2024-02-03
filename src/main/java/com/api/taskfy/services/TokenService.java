package com.api.taskfy.services;

import com.api.taskfy.errors.user.InvalidTokenException;
import com.api.taskfy.errors.user.UserNotFoundException;
import com.api.taskfy.modules.user.entities.RefreshToken;
import com.api.taskfy.modules.user.entities.User;
import com.api.taskfy.modules.user.repositories.RefreshTokenRepository;
import com.api.taskfy.modules.user.repositories.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UserNotFoundException {
        var userFound = this.userRepository.findByEmail(email);

        if (userFound.isEmpty()) {
            throw new UserNotFoundException();
        }

        return userFound.get();
    }

    public String generateToken(User user, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            var expirationDate = LocalDateTime.now().plusHours(4).toInstant(ZoneOffset.of("-03:00"));

            return JWT.create()
                    .withIssuer("taskfy-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(expirationDate)
                    .sign(algorithm);
        } catch(JWTCreationException exception) {
            throw new RuntimeException("Generation token error", exception);
        }
    }

    public RefreshToken generateRefreshToken(User user) {
        var userId = user.getId();

        var currentRefreshToken = this.refreshTokenRepository.findByUserId(userId);

        if (currentRefreshToken != null) {
            this.refreshTokenRepository.deleteById(currentRefreshToken.getId());
        }

        var expirationDate = LocalDateTime.now().plusHours(4);

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUserId(userId);
        refreshToken.setExpiresAt(expirationDate);

        return this.refreshTokenRepository.save(refreshToken);
    }

    public String validateAuthToken(String token, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("taskfy-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch(JWTVerificationException exception) {
            throw new InvalidTokenException();
        }
    }

    public Boolean validateToken(String token, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            JWT.require(algorithm)
                    .withIssuer("taskfy-api")
                    .build()
                    .verify(token);

            return true;
        } catch(JWTVerificationException exception) {
            return false;
        }
    }
}
