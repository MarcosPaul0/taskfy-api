package com.api.taskfy.modules.user.useCases.authRefresh;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.user.dtos.RefreshAuthDto;
import com.api.taskfy.modules.user.dtos.TokenResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.AUTH)
public class AuthRefreshController {
    @Autowired
    AuthRefreshService authRefreshService;

    @PostMapping("refresh")
    public ResponseEntity<TokenResponseDto> handle(@Valid @RequestBody RefreshAuthDto refreshAuthDto) {
        var refreshResponse = this.authRefreshService.execute(refreshAuthDto);

        return ResponseEntity.ok(refreshResponse);
    }
}
