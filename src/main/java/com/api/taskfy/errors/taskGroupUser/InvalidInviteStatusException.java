package com.api.taskfy.errors.taskGroupUser;

import com.api.taskfy.errors.DefaultException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidInviteStatusException extends DefaultException {
    public static String message = "Invalid invite status";

    public InvalidInviteStatusException() {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
