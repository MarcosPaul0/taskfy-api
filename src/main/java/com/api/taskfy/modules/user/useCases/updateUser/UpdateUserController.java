package com.api.taskfy.modules.user.useCases.updateUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.taskfy.modules.user.dtos.UpdateUserDto;
import com.api.taskfy.modules.user.entities.User;
import com.api.taskfy.constants.Routes;

import jakarta.validation.Valid;

@RestController
@RequestMapping(Routes.USER)
public class UpdateUserController {
    @Autowired
    UpdateUserService updateUserService;

    @PatchMapping
    public ResponseEntity<User> handle(
        @AuthenticationPrincipal User user,
        @Valid @RequestBody UpdateUserDto updateUserDto
    ) {
        User updatedUser = this.updateUserService.execute(user.getId(), updateUserDto);

        return ResponseEntity.ok(updatedUser);
    }
}
