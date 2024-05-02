package com.api.taskfy.errors.user;

import com.api.taskfy.errors.DefaultException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserNotHavePermissionException extends DefaultException {
    public static String message = "User does not have permission to perform this action";

    public UserNotHavePermissionException() {
        super(message, HttpStatus.FORBIDDEN);
    }
}
