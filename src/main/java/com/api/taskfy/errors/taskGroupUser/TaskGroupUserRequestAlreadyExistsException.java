package com.api.taskfy.errors.taskGroupUser;

import com.api.taskfy.errors.DefaultException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TaskGroupUserRequestAlreadyExistsException extends DefaultException {
    public static String message = "Task group user request already exists";

    public TaskGroupUserRequestAlreadyExistsException() {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
