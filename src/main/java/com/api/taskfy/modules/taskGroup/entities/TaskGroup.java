package com.api.taskfy.modules.taskGroup.entities;

import com.api.taskfy.constants.Entities;
import com.api.taskfy.constants.Tables;
import com.api.taskfy.modules.taskGroup.dtos.CreateTaskGroupDto;
import com.api.taskfy.modules.user.entities.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = Tables.TASK_GROUP)
@Entity(name = Entities.TASK_GROUP)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class TaskGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "primary_color")
    private String primaryColor;

    @Column(name = "banner_url")
    private String bannerUrl;

    @Column(name = "is_private")
    private Boolean isPrivate;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "owner_id")
    private User owner;

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

    public TaskGroup(CreateTaskGroupDto createTaskGroupDto) {
        this.name = createTaskGroupDto.name;
        this.description = createTaskGroupDto.description;
        this.primaryColor = createTaskGroupDto.primaryColor;
        this.isPrivate = createTaskGroupDto.isPrivate;
    }
}
