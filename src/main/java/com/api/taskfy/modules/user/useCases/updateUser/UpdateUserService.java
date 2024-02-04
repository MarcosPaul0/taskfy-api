package com.api.taskfy.modules.user.useCases.updateUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.taskfy.errors.user.UserNotFoundException;
import com.api.taskfy.modules.user.dtos.UpdateUserDto;
import com.api.taskfy.modules.user.entities.User;
import com.api.taskfy.modules.user.repositories.UserRepository;

@Service
public class UpdateUserService {
    @Autowired
    UserRepository userRepository;

    public User execute(String userId, UpdateUserDto updateUserDto) {
      var userFound = this.userRepository
                        .findById(userId);

      if (userFound.isEmpty()) {
          throw new UserNotFoundException();
      }

      var user = userFound.get();

      if (updateUserDto.name != null && !updateUserDto.name.isBlank()) {
          user.setName(updateUserDto.name);
      }

      return this.userRepository.save(user);
    }
}
