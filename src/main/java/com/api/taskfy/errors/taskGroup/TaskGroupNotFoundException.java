package com.api.taskfy.errors.taskGroup;

import com.api.taskfy.errors.DefaultException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TaskGroupNotFoundException extends DefaultException {
    public static String message = "Task group not found";

    public TaskGroupNotFoundException() {
        super(message, HttpStatus.NOT_FOUND);
    }
}
