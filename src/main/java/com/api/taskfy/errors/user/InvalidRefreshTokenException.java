package com.api.taskfy.errors.user;

import org.springframework.http.HttpStatus;

import com.api.taskfy.errors.DefaultException;

import lombok.Getter;

@Getter
public class InvalidRefreshTokenException extends DefaultException {
    public static String message = "Invalid refresh token";

    public InvalidRefreshTokenException() {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
