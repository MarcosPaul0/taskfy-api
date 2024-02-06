package com.api.taskfy.modules.user.useCases.confirmEmail;

import com.api.taskfy.constants.Routes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.AUTH)
public class ConfirmUserEmailController {
    @Autowired
    ConfirmUserEmailService confirmEmailService;

    @GetMapping("confirm/{confirmToken}")
    public ResponseEntity<Void> handle(@PathVariable("confirmToken") String confirmToken) {
        var confirmTokenIsValid = this.confirmEmailService.execute(confirmToken);

        if (confirmTokenIsValid) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
