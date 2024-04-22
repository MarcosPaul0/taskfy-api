package com.api.taskfy.errors.taskGroupUser;

import com.api.taskfy.errors.DefaultException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TaskGroupUserNotFoundException extends DefaultException {
    public static String message = "Task group user not found";

    public TaskGroupUserNotFoundException() {
        super(message, HttpStatus.FORBIDDEN);
    }
}
