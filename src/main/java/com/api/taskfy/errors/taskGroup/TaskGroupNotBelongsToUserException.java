package com.api.taskfy.errors.taskGroup;

import com.api.taskfy.errors.DefaultException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TaskGroupNotBelongsToUserException extends DefaultException {
    public static String message = "Task group not belongs to user";

    public TaskGroupNotBelongsToUserException() {
        super(message, HttpStatus.FORBIDDEN);
    }
}
