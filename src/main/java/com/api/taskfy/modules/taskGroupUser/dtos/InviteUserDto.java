package com.api.taskfy.modules.taskGroupUser.dtos;

import com.api.taskfy.constants.RegularExpressions;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class InviteUserDto {
    @NotNull(message = "User id is required")
    @Email(message = "Email is invalid")
    public String email;

    @NotNull(message = "Task group id is required")
    @Pattern(regexp = RegularExpressions.UUID,
            message = "Invalid task group UUID")
    public String groupId;

    @NotNull(message = "Task group role is required")
    @Pattern(regexp = RegularExpressions.TASK_GROUP_ROLE,
            message = "Invalid task group role")
    public String taskGroupRole;
}
