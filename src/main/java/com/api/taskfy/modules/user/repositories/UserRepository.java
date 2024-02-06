package com.api.taskfy.modules.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.taskfy.modules.user.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
