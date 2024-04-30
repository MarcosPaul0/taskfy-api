package com.api.taskfy.modules.ranking.entities;

import com.api.taskfy.constants.Entities;
import com.api.taskfy.constants.Tables;
import com.api.taskfy.modules.ranking.dtos.CreateRankingDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = Tables.RANKING)
@Entity(name = Entities.RANKING)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Ranking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "task_group_id")
    private String groupId;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ranking", cascade = CascadeType.ALL)
    private List<UserPlacement> userPlacementList;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (updatedAt == null) {
            updatedAt = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Ranking (CreateRankingDto createRankingDto) {
        this.dueDate = createRankingDto.dueDate;
        this.groupId = createRankingDto.groupId;
    }
}
