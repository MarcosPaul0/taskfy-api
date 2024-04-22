package com.api.taskfy.errors.taskGroupUser;

import com.api.taskfy.errors.DefaultException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidRequestStatusException extends DefaultException {
    public static String message = "Invalid request status";

    public InvalidRequestStatusException() {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
