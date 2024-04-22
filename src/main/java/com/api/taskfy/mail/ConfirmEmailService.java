package com.api.taskfy.mail;

import com.api.taskfy.modules.user.entities.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class ConfirmEmailService {
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Async
    public void sendMail(User user, String confirmationToken) throws MessagingException {
        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        Context context = new Context();
        context.setVariable("name", user.getName());
        context.setVariable("confirmationToken", confirmationToken);

        String template = this.templateEngine.process("confirmation", context);

        message.setTo(user.getEmail());
        message.setSubject("Taskfy | Confirme seu Email");
        message.setText(template, true);

        this.javaMailSender.send(mimeMessage);
    }
}
