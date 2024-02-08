package com.api.taskfy.modules.user.useCases.getUser;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.taskfy.constants.Routes;

@RestController
@RequestMapping(Routes.USER)
public class GetUserController {

  @GetMapping
  public ResponseEntity<UserDetails> handle(@AuthenticationPrincipal UserDetails user) {
    return ResponseEntity.ok(user);
  }
}
