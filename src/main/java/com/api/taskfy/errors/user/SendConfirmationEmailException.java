package com.api.taskfy.errors.user;

import com.api.taskfy.errors.DefaultException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SendConfirmationEmailException extends DefaultException {
    public static String message = "Confirmation email sent error";

    public SendConfirmationEmailException() {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
