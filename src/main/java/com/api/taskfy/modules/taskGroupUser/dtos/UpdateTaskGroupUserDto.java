package com.api.taskfy.modules.taskGroupUser.dtos;

import com.api.taskfy.constants.RegularExpressions;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateTaskGroupUserDto {
    @NotNull(message = "Task group role is required")
    @Pattern(regexp = RegularExpressions.TASK_GROUP_ROLE,
            message = "Invalid task group role")
    public String taskGroupRole;
}
