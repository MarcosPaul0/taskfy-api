package com.api.taskfy.modules.task.enums;

import com.api.taskfy.errors.task.InvalidTaskStatusException;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum TaskStatus {
    ON_HOLD,
    OPEN,
    CONCLUDED,
    IN_PROGRESS,
    CHECKED;

    @JsonCreator
    public static TaskStatus fromString(String value) {
        try {
            return TaskStatus.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidTaskStatusException();
        }
    }
}
