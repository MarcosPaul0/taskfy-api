package com.api.taskfy.modules.taskGroupUser.enums;

import com.api.taskfy.errors.taskGroupUser.InvalidTaskGroupRoleException;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum TaskGroupRole {
    OWNER,
    MANAGER,
    NORMAL;

    @JsonCreator
    public static TaskGroupRole fromString(String value) {
        try {
            return TaskGroupRole.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidTaskGroupRoleException();
        }
    }
}
