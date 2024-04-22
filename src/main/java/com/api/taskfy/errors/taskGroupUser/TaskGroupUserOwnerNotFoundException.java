package com.api.taskfy.errors.taskGroupUser;

import com.api.taskfy.errors.DefaultException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TaskGroupUserOwnerNotFoundException extends DefaultException {
    public static String message = "Task group user owner not found";

    public TaskGroupUserOwnerNotFoundException() {
        super(message, HttpStatus.NOT_FOUND);
    }
}
