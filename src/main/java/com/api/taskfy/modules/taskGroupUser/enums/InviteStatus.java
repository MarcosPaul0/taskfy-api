package com.api.taskfy.modules.taskGroupUser.enums;

import com.api.taskfy.errors.taskGroupUser.InvalidInviteStatusException;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum InviteStatus {
    PENDING,
    ACCEPTED,
    REJECTED;

    @JsonCreator
    public static InviteStatus fromString(String value) {
        try {
            return InviteStatus.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidInviteStatusException();
        }
    }
}
