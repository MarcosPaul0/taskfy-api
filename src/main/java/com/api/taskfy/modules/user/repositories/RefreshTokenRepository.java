package com.api.taskfy.modules.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.taskfy.modules.user.entities.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    RefreshToken findByUserId(String userId);
}