package com.api.taskfy.modules.user.useCases.authUser;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.user.dtos.AuthUserDto;
import com.api.taskfy.modules.user.dtos.TokenResponseDto;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.AUTH)
public class AuthUserController {
    @Autowired
    AuthUserService authUserService;

    @PostMapping
    public ResponseEntity<TokenResponseDto> handle(@Valid @RequestBody AuthUserDto authUserDto, HttpServletResponse httpResponse) {
        var authResponse = this.authUserService.execute(authUserDto);

        httpResponse.setHeader("Access-Control-Allow-Origin", "*");

        return ResponseEntity.ok(authResponse);
    }
}
