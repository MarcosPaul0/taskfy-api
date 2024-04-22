package com.api.taskfy.errors.user;

import com.api.taskfy.errors.DefaultException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RefreshTokenExpiredException extends DefaultException {
    public static String message = "User already exists";

    public RefreshTokenExpiredException() {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
