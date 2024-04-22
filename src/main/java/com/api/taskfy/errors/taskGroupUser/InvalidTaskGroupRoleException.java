package com.api.taskfy.errors.taskGroupUser;

import com.api.taskfy.errors.DefaultException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidTaskGroupRoleException extends DefaultException {
    public static String message = "Invalid task group role";

    public InvalidTaskGroupRoleException() {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
