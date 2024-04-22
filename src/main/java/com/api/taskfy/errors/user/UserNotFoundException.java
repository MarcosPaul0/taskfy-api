package com.api.taskfy.errors.user;

import org.springframework.http.HttpStatus;

import com.api.taskfy.errors.DefaultException;

import lombok.Getter;

@Getter
public class UserNotFoundException extends DefaultException {
    public static String message = "User not found";

    public UserNotFoundException() {
        super(message, HttpStatus.NOT_FOUND);
    }
}
