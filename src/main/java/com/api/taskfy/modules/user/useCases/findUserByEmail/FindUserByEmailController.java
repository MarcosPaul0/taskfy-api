package com.api.taskfy.modules.user.useCases.findUserByEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.user.dtos.FindUserByEmailDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping(Routes.USER)
public class FindUserByEmailController {
  @Autowired
  FindUserByEmailService findUserByEmailService;

  @PostMapping("byEmail")
  public ResponseEntity<UserDetails> handle(@Valid @RequestBody FindUserByEmailDto findUserByEmailDto ) {
    var userFound = this.findUserByEmailService.execute(findUserByEmailDto.email); 

    return ResponseEntity.ok(userFound);
  }
}
