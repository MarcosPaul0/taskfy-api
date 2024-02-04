package com.api.taskfy.modules.user.useCases.deleteUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.user.entities.User;

@RestController
@RequestMapping(Routes.USER)
public class DeleteUserController {
  @Autowired
  DeleteUserService deleteUserService;
  
  @DeleteMapping
  public ResponseEntity<Void> handle(@AuthenticationPrincipal User user) {
    this.deleteUserService.execute(user.getId());

    return ResponseEntity.ok().build();
  }
}
