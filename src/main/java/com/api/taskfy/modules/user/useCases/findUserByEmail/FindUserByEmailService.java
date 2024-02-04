package com.api.taskfy.modules.user.useCases.findUserByEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.api.taskfy.errors.user.UserNotFoundException;
import com.api.taskfy.modules.user.repositories.UserRepository;

@Service
public class FindUserByEmailService {
  @Autowired
  UserRepository userRepository;

  public UserDetails execute(String email) {
    var userFound = this.userRepository.findByEmail(email);

    if (userFound.isEmpty()) {
      throw new UserNotFoundException();
    }

    return userFound.get();
  }
}
