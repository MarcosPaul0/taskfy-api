package com.api.taskfy.errors;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class DefaultException extends RuntimeException {
    public HttpStatus status;

    public DefaultException(String message, HttpStatus status) {
      super(message);
      this.status = status;
  }
}
