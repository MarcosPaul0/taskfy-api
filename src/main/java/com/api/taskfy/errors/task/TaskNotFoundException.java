package com.api.taskfy.errors.task;

import com.api.taskfy.errors.DefaultException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TaskNotFoundException extends DefaultException {
    public static String message = "Task not found";

    public TaskNotFoundException() {
        super(message, HttpStatus.NOT_FOUND);
    }
}
