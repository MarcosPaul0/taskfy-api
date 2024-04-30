package com.api.taskfy.modules.taskGroupUser.dtos;

import com.api.taskfy.constants.RegularExpressions;
import com.api.taskfy.modules.taskGroupUser.enums.InviteStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class RespondTaskGroupInviteDto {
    @NotNull(message = "Invite status is required")
    @Pattern(regexp = RegularExpressions.TASK_GROUP_STATUS,
            message = "Invalid invite status")
    public String inviteStatus;
}
