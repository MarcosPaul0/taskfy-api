package com.api.taskfy.modules.reward.repositories;

import com.api.taskfy.modules.reward.entities.Reward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RewardRepository extends JpaRepository<Reward, String> {
    List<Reward> findAllByRankingIdOrderByPositionAsc(String rankingId);
}
