package com.api.taskfy.modules.ranking.repositories;

import com.api.taskfy.modules.ranking.entities.Ranking;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RankingRepository extends JpaRepository<Ranking, String> {
    @Transactional
    @Modifying
    @Query("FROM ranking r WHERE r.createdAt < :date AND r.dueDate > :date AND r.groupId = :groupId")
    List<Ranking> findByPeriodAndGroupId(@Param("date") LocalDateTime date, String groupId);
    List<Ranking> findAllByGroupIdOrderByDueDateAsc(String groupId);
    @Query("FROM ranking r WHERE r.groupId = :groupId AND r.dueDate > CURRENT_TIMESTAMP ORDER BY r.dueDate ASC")
    Optional<Ranking> findCurrentRankingByGroupId(@Param("groupId") String groupId);
}
