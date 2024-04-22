package com.api.taskfy.errors.task;

import com.api.taskfy.errors.DefaultException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidTaskStatusException extends DefaultException {
    public static String message = "Invalid task status";

    public InvalidTaskStatusException() {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
