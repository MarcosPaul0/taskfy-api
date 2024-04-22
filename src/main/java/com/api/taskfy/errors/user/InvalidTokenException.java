package com.api.taskfy.errors.user;

import com.api.taskfy.errors.DefaultException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidTokenException extends DefaultException {
    public static String message = "Invalid token";

    public InvalidTokenException() {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
