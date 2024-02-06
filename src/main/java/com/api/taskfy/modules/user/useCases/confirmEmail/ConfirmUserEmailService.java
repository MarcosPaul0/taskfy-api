package com.api.taskfy.modules.user.useCases.confirmEmail;

import com.api.taskfy.errors.user.UserNotFoundException;
import com.api.taskfy.modules.user.entities.User;
import com.api.taskfy.modules.user.repositories.UserRepository;
import com.api.taskfy.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConfirmUserEmailService {
    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    @Value("${email.confirmation.secret}")
    private String secret;

    public Boolean execute(String confirmToken) {
        var userEmail = this.tokenService.validateAuthToken(confirmToken, secret);

        if (userEmail == null) {
            return false;
        }

        var userFound = this.userRepository.findByEmail(userEmail);

        if (userFound.isEmpty()) {
            throw new UserNotFoundException();
        }

        var user = userFound.get();

        user.setIsActive(true);

        this.userRepository.save(user);

        return true;
    }
}
