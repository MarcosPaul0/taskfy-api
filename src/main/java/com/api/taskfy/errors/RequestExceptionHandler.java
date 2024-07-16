package com.api.taskfy.errors;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.api.taskfy.errors.ranking.*;
import com.api.taskfy.errors.reward.RewardNotFoundException;
import com.api.taskfy.errors.task.*;
import com.api.taskfy.errors.taskGroup.*;
import com.api.taskfy.errors.taskGroupUser.*;
import com.api.taskfy.errors.user.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RequestExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleInvalidArgument(MethodArgumentNotValidException exception) {
        StringBuilder errorsBuilder = new StringBuilder();

        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errorsBuilder.append(fieldError.getField())
                    .append(": ")
                    .append(fieldError.getDefaultMessage())
                    .append("; ");
        });

        String formattedErrors = errorsBuilder.toString();

        ApiException apiException = new ApiException(
                "Validation error: " + formattedErrors,
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {DefaultException.class})
    public ResponseEntity<Object> handleException(DefaultException exception) {
        ApiException apiException = new ApiException(
                exception.getMessage(),
                exception.getStatus(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, exception.getStatus());
    }
}
