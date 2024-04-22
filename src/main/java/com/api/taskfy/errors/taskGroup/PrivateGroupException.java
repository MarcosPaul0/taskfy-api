package com.api.taskfy.errors.taskGroup;

import com.api.taskfy.errors.DefaultException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PrivateGroupException extends DefaultException {
    public static String message = "Task group is private";

    public PrivateGroupException() {
        super(message, HttpStatus.FORBIDDEN);
    }
}
