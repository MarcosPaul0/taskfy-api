package com.api.taskfy.modules.ranking.dtos;

import com.api.taskfy.constants.RegularExpressions;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class CreateRankingDto {
    @NotNull(message = "Task group id is required")
    @Pattern(regexp = RegularExpressions.UUID, message = "Invalid task group id")
    public String groupId;

    @NotNull(message = "Due date is required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Future(message = "The due date must be a future date")
    public LocalDateTime dueDate;
}
