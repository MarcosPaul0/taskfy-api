package com.api.taskfy.modules.user.useCases.sendConfirmationEmail;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.user.dtos.SendConfirmationEmailDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.AUTH)
public class SendConfirmationEmailController {
    @Autowired
    SendConfirmationEmailService sendConfirmationEmailService;

    @PostMapping("/confirm")
    public ResponseEntity<Void> handle(@Valid @RequestBody SendConfirmationEmailDto sendConfirmationEmailDto) {
        this.sendConfirmationEmailService.execute(sendConfirmationEmailDto);

        return ResponseEntity.ok().build();
    }
}
