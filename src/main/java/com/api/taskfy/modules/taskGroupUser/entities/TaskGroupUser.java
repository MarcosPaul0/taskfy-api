package com.api.taskfy.modules.taskGroupUser.entities;

import com.api.taskfy.constants.Entities;
import com.api.taskfy.constants.Tables;
import com.api.taskfy.modules.taskGroupUser.dtos.CreateTaskGroupUserDto;
import com.api.taskfy.modules.taskGroupUser.enums.InviteStatus;
import com.api.taskfy.modules.taskGroupUser.enums.RequestStatus;
import com.api.taskfy.modules.taskGroupUser.enums.TaskGroupRole;
import com.api.taskfy.modules.user.entities.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = Tables.TASK_GROUP_USER)
@Entity(name = Entities.TASK_GROUP_USER)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class TaskGroupUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "task_group_id")
    private String groupId;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_group_role")
    private TaskGroupRole taskGroupRole;

    @Enumerated(EnumType.STRING)
    @Column(name = "invite_status")
    private InviteStatus inviteStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_status")
    private RequestStatus requestStatus;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(cascade = CascadeType.ALL)
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

    public TaskGroupUser(CreateTaskGroupUserDto createTaskGroupUserDto) {
        this.groupId = createTaskGroupUserDto.groupId;
        this.taskGroupRole = TaskGroupRole.valueOf(createTaskGroupUserDto.taskGroupRole);
    }
}
