package com.api.taskfy.modules.reward.entities;


import com.api.taskfy.constants.Entities;
import com.api.taskfy.constants.Tables;
import com.api.taskfy.modules.reward.dtos.CreateRewardDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = Tables.REWARD)
@Entity(name = Entities.REWARD)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "ranking_id")
    private String rankingId;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "position")
    private Integer position;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;


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

    public Reward (CreateRewardDto createRewardDto) {
        this.title = createRewardDto.title;
        this.description = createRewardDto.description;
        this.rankingId = createRewardDto.rankingId;
    }
}
