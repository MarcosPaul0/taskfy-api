package com.api.taskfy.modules.user.repositories;

import com.api.taskfy.modules.user.entities.User;

import java.util.List;

public interface CustomUserRepository {
    List<User> findByGroup(String groupId, String emailPattern, String usernamePattern);
}
