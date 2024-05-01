package com.api.taskfy.modules.taskGroupUser.dtos;

import com.api.taskfy.constants.RegularExpressions;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

public class InviteTaskGroupUsersDto {
    @NotNull(message = "Task group id is required")
    @Pattern(regexp = RegularExpressions.UUID,
            message = "Invalid task group UUID")
    public String groupId;

    @NotNull(message = "User email list is required")
    @UniqueElements(message = "Emails should be unique")
    public List<String> emailList;
}
