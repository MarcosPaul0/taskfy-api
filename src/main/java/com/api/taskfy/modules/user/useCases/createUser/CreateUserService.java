package com.api.taskfy.modules.user.useCases.createUser;

import com.api.taskfy.errors.user.SendConfirmationEmailException;
import com.api.taskfy.mail.ConfirmEmailService;
import com.api.taskfy.services.TokenService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.taskfy.errors.user.UserAlreadyExistsException;
import com.api.taskfy.modules.user.dtos.CreateUserDto;
import com.api.taskfy.modules.user.entities.User;
import com.api.taskfy.modules.user.repositories.UserRepository;

@Service
public class CreateUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Autowired
    ConfirmEmailService mailService;

    @Value("${email.confirmation.secret}")
    private String secret;

    public User execute(CreateUserDto createUserDto) {
        var userFound = this.userRepository.findByEmail(createUserDto.email);

        if (userFound.isPresent()) {
          throw new UserAlreadyExistsException();
        }

        String encryptedPassword = this.passwordEncoder.encode(createUserDto.password);

        User newUser = new User(createUserDto);

        newUser.setPassword(encryptedPassword);

        try {
            var confirmationToken = this.tokenService.generateToken(newUser, secret);

            this.mailService.sendMail(newUser, confirmationToken);
        } catch (MessagingException error) {
            throw new SendConfirmationEmailException();
        }

        return this.userRepository.save(newUser);
    }
}
