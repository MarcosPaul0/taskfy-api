package com.api.taskfy.modules.taskGroupUser.dtos.response;

import com.api.taskfy.modules.taskGroupUser.enums.InviteStatus;
import com.api.taskfy.modules.taskGroupUser.enums.TaskGroupRole;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskGroupUserResponseDto {
    private String id;
    private TaskGroupRole taskGroupRole;
    private InviteStatus inviteStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String userId;
    private TaskGroupResponseDto taskGroup;
}
