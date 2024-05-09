package com.api.taskfy.modules.task.entities;

import com.api.taskfy.constants.Entities;
import com.api.taskfy.constants.Tables;
import com.api.taskfy.modules.task.dtos.CreateTaskDto;
import com.api.taskfy.modules.task.enums.TaskStatus;
import com.api.taskfy.modules.user.entities.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = Tables.TASK)
@Entity(name = Entities.TASK)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "task_group_id")
    private String groupId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TaskStatus status;

    @Column(name = "points")
    private Integer points;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JsonManagedReference
    @JoinColumn(name = "user_id")
    private User user;

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

    public Task(CreateTaskDto createTaskDto) {
        this.groupId = createTaskDto.groupId;
        this.title = createTaskDto.title;
        this.description = createTaskDto.description;
        this.status = TaskStatus.valueOf(createTaskDto.status);
        this.points = createTaskDto.points;
        this.dueDate = createTaskDto.dueDate;
    }
}
