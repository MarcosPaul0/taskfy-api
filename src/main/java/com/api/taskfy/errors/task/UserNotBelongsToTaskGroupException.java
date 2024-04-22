package com.api.taskfy.errors.task;

import com.api.taskfy.errors.DefaultException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserNotBelongsToTaskGroupException extends DefaultException {
    public static String message = "User does not belong to task group";

    public UserNotBelongsToTaskGroupException() {
        super(message, HttpStatus.FORBIDDEN);
    }
}
