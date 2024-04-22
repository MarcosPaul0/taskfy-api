package com.api.taskfy.errors.taskGroupUser;

import com.api.taskfy.errors.DefaultException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TaskGroupUserNotBelongsException extends DefaultException {
    public static String message = "Task group user not belongs";

    public TaskGroupUserNotBelongsException() {
        super(message, HttpStatus.FORBIDDEN);
    }
}
