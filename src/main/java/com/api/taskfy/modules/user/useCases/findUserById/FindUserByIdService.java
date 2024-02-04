package com.api.taskfy.modules.user.useCases.findUserById;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.taskfy.modules.user.entities.User;
import com.api.taskfy.modules.user.repositories.UserRepository;
import com.api.taskfy.errors.user.UserNotFoundException;

@Service
public class FindUserByIdService {
  @Autowired
  UserRepository userRepository;

  public User execute(String userId) {
    var userFound = this.userRepository.findById(userId);

    if (userFound.isEmpty()) {
      throw new UserNotFoundException();
    }
    
    return userFound.get();
  }
}
