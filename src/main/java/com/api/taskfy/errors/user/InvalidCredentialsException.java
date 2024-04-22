package com.api.taskfy.errors.user;

import com.api.taskfy.errors.DefaultException;
import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends DefaultException {
    public static String message = "Invalid credentials";

    public InvalidCredentialsException() {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
