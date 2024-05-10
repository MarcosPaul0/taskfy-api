package com.api.taskfy.modules.task.dtos;

import com.api.taskfy.constants.RegularExpressions;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateTaskDto {
    @Pattern(regexp = RegularExpressions.UUID, message = "Invalid user id")
    public String userId;

    @NotBlank(message = "Title is required")
    @Length(min = 10, message = "Title must have at least 10 characters")
    @Length(max = 100, message = "Title must have a maximum of 100 characters")
    public String title;

    @NotBlank(message = "Description is empty")
    @Length(min = 10, message = "Description must have at least 10 characters")
    @Length(max = 500, message = "Description must have a maximum of 500 characters")
    public String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Future(message = "The due date must be a future date")
    public LocalDateTime dueDate;

    @Pattern(regexp = RegularExpressions.TASK_STATUS, message = "Invalid task status")
    public String status;

    @Min(value = 1, message = "Minimum number of points is 1")
    @Max(value = 144, message = "Maximum number of points is 144")
    public Integer points;

    public Boolean isActive;
}
