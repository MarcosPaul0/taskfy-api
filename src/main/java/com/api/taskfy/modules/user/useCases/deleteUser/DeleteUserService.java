package com.api.taskfy.modules.user.useCases.deleteUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.taskfy.modules.user.repositories.UserRepository;

@Service
public class DeleteUserService {
    @Autowired
    UserRepository userRepository;

    public void execute(String userId) {
      this.userRepository.deleteById(userId);
    } 
}
