package com.api.taskfy.modules.user.useCases.findUserById;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.user.entities.User;

@RestController
@RequestMapping(Routes.USER)
public class FindUserByIdController {
  @Autowired
  FindUserByIdService findUserByIdService;

  @GetMapping("/{userId}")
  public ResponseEntity<User> handle(@PathVariable("userId") String userId) {
    User user = this.findUserByIdService.execute(userId);

    return ResponseEntity.ok(user);
  }

}
