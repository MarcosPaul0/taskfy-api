package com.api.taskfy.errors;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiException {
    private final String message;
    private final HttpStatus status;
    private final ZonedDateTime timestamp;
}
