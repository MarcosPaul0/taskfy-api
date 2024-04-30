package com.api.taskfy.modules.taskGroupUser.dtos;

import com.api.taskfy.constants.RegularExpressions;
import com.api.taskfy.modules.taskGroupUser.enums.InviteStatus;
import com.api.taskfy.modules.taskGroupUser.enums.RequestStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class RespondTaskGroupRequestDto {
    @NotNull(message = "Request status is required")
    @Pattern(regexp = RegularExpressions.TASK_GROUP_STATUS,
            message = "Invalid request status")
    public String requestStatus;
}
