package com.api.taskfy.modules.ranking.repositories;

import com.api.taskfy.modules.ranking.entities.UserPlacement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPlacementRepository extends JpaRepository<UserPlacement, String> {
}
