package com.api.taskfy.errors.taskGroupUser;

import com.api.taskfy.errors.DefaultException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TaskGroupUserInvitationAlreadyExistsException extends DefaultException {
    public static String message = "Task group user invitation already exists";

    public TaskGroupUserInvitationAlreadyExistsException() {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
