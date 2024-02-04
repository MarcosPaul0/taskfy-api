package com.api.taskfy.modules.user.useCases.createUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.user.dtos.CreateUserDto;
import com.api.taskfy.modules.user.entities.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping(Routes.USER)
public class CreateUserController {
    @Autowired
    private CreateUserService createUserService;
    
    @PostMapping
    public ResponseEntity<User> handle(@Valid @RequestBody CreateUserDto createUserDto) {
        User newUser = this.createUserService.execute(createUserDto);

        return ResponseEntity.ok(newUser);
    }
}
