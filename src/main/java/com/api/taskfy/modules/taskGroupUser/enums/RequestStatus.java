package com.api.taskfy.modules.taskGroupUser.enums;

import com.api.taskfy.errors.taskGroupUser.InvalidRequestStatusException;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum RequestStatus {
    PENDING,
    ACCEPTED,
    REJECTED;

    @JsonCreator
    public static RequestStatus fromString(String value) {
        try {
            return RequestStatus.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidRequestStatusException();
        }
    }
}
