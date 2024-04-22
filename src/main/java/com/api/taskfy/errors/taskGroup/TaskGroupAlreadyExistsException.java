package com.api.taskfy.errors.taskGroup;

import com.api.taskfy.errors.DefaultException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TaskGroupAlreadyExistsException extends DefaultException {
    public static String message = "Task group already exists";

    public TaskGroupAlreadyExistsException() {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
