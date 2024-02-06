package com.api.taskfy.modules.user.useCases.sendConfirmationEmail;

import com.api.taskfy.errors.user.SendConfirmationEmailException;
import com.api.taskfy.errors.user.UserNotFoundException;
import com.api.taskfy.mail.ConfirmEmailService;
import com.api.taskfy.modules.user.dtos.SendConfirmationEmailDto;
import com.api.taskfy.modules.user.entities.User;
import com.api.taskfy.modules.user.repositories.UserRepository;
import com.api.taskfy.services.TokenService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SendConfirmationEmailService {
    @Autowired
    ConfirmEmailService mailService;

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    @Value("${email.confirmation.secret}")
    private String secret;

    public void execute(SendConfirmationEmailDto sendConfirmationEmailDto){
        var userFound = this.userRepository.findByEmail(sendConfirmationEmailDto.email);

        if (userFound.isEmpty()) {
            throw new UserNotFoundException();
        }

        try {
            var user = userFound.get();

            var confirmationToken = this.tokenService.generateToken(user, secret);

            this.mailService.sendMail(user, confirmationToken);
        } catch (MessagingException error) {
            throw new SendConfirmationEmailException();
        }
    }
}
