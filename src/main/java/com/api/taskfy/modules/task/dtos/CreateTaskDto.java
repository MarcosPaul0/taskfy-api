package com.api.taskfy.modules.task.dtos;

import com.api.taskfy.constants.RegularExpressions;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class CreateTaskDto {
    @NotNull(message = "User id is required")
    @Pattern(regexp = RegularExpressions.UUID, message = "Invalid user id")
    public String userId;

    @NotNull(message = "Task group id is required")
    @Pattern(regexp = RegularExpressions.UUID, message = "Invalid task group id")
    public String groupId;

    @NotNull(message = "Title is required")
    @NotBlank(message = "Title is required")
    @Length(min = 10, message = "Title must have at least 10 characters")
    @Length(max = 100, message = "Title must have a maximum of 100 characters")
    public String title;

    @NotBlank(message = "Description is empty")
    @Length(min = 10, message = "Description must have at least 10 characters")
    @Length(max = 500, message = "Description must have a maximum of 500 characters")
    public String description;

    @NotNull(message = "Due date is required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Future(message = "The due date must be a future date")
    public LocalDateTime dueDate;

    @NotNull(message = "Status is required")
    @Pattern(regexp = RegularExpressions.TASK_STATUS, message = "Invalid task status")
    public String status;

    @NotNull(message = "Points is required")
    @Min(value = 1, message = "Minimum number of points is 1")
    @Max(value = 144, message = "Maximum number of points is 144")
    public Integer points;
}
