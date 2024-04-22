package com.api.taskfy.errors.user;

import org.springframework.http.HttpStatus;

import com.api.taskfy.errors.DefaultException;

import lombok.Getter;

@Getter
public class UserAlreadyExistsException extends DefaultException {
    public static String message = "User already exists";

    public UserAlreadyExistsException() {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
